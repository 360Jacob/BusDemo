package com.jacob.lib_data_service.remote.interceptor

import com.jacob.lib_data_service.config.NetAppContext
import com.jacob.lib_data_service.error.ApiException
import com.jacob.lib_data_service.error.PARSE_ERROR
import com.jacob.lib_data_service.utils.ext.view.showToast
import com.jacob.lib_log.KLog
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class ResponseInterceptorNew : ResponseBodyInterceptor() {
    override fun intercept(response: Response, url: String, body: String): Response {

        if (!response.isSuccessful) {
            val ex = ApiException(response.code)
            ex.message = errorManager.getError(response.code).description
            ex.message.showToast(NetAppContext.getContext())
            KLog.e(TAG, ex.message, ex)
            throw ex
        }

//        val jsonObject = JSONObject(body)
//
//        val resp = jsonObject.getJSONObject("response")
//        val status = resp.getInt("code")
//        val message = resp.getString("msg")
//
//        if (0 != status) {
//            //TODO 拦截重新登录，广播
//        }


        return try {

            val jsonObject = JSONObject(body)

            val resp = jsonObject?.getJSONObject("response")
            val status = resp.getInt("code")
            val message = resp?.getString("msg")
            if (0 == status) {
                val responseBody = body?.toResponseBody()
                response.newBuilder().body(responseBody).build()
            } else {
                throw ApiException(status, message ?: "")
            }
        } catch (e: Exception) {
            throw ApiException(PARSE_ERROR, errorManager.getError(PARSE_ERROR).description, e)
        }
    }
}