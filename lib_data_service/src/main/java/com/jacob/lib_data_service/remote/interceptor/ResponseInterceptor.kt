package com.jacob.lib_data_service.remote.interceptor

import android.text.TextUtils
import android.util.Log
import com.jacob.lib_data_service.config.Encoding
import com.jacob.lib_data_service.config.NetAppContext
import com.jacob.lib_data_service.config.contentTypeValue
import com.jacob.lib_data_service.error.ApiException
import com.jacob.lib_data_service.error.NULL_DATA
import com.jacob.lib_data_service.error.PARSE_ERROR
import com.jacob.lib_data_service.error.mapper.ErrorManager
import com.jacob.lib_data_service.error.mapper.ErrorMapper
import com.jacob.lib_data_service.utils.ext.view.showToast
import com.jacob.lib_log.KLog
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.http.RealResponseBody
import okhttp3.internal.http.hasBody
import okio.BufferedSource
import okio.GzipSource
import okio.buffer
import org.json.JSONObject
import java.nio.charset.Charset
import android.R.string
import android.util.Xml
import com.google.gson.Gson
import com.jacob.lib_domain.base.BaseResponse


/**
 * Describe:
 * <p>相应数据拦截器</p>
 *
 */
class ResponseInterceptor : Interceptor {

    companion object {
        val TAG: String = ResponseInterceptor::class.java.simpleName
    }

    private val errorManager: ErrorManager = ErrorManager(ErrorMapper())

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val body = bufferBody(response)

        // 网络相应不成功返回
        if (!response.isSuccessful) {
            val ex = ApiException(response.code)
            ex.message = errorManager.getError(response.code).description
            ex.message.showToast(NetAppContext.getContext())
            KLog.e(TAG, ex.message, ex)
            throw ex
        }

        return try {
            if (TextUtils.isEmpty(body) || "null".equals(body, ignoreCase = true)) {
                throw ApiException(NULL_DATA, errorManager.getError(NULL_DATA).description)
            }
            val jsonObject = JSONObject(body)

            val resp = jsonObject.getJSONObject("response")
            val status = resp.getInt("code")
            val message = resp.getString("msg")
            if (0 == status) {
                response.newBuilder()
                    .body(
                        ResponseBody.create(
                            contentTypeValue.toMediaTypeOrNull(),
                            Gson().toJson(resp)
                        )
                    )
                    .build()
            } else {
                throw ApiException(status, message ?: "")
            }
        } catch (e: Exception) {
            throw ApiException(PARSE_ERROR, errorManager.getError(PARSE_ERROR).description, e)
        }
    }

    private fun bufferBody(response: Response): String {
        val source = response.body!!.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer()
        return buffer.clone().readString(Charset.forName(Encoding))
    }

}
