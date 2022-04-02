package com.jacob.lib_data_service.remote.interceptor

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jacob.lib_data_service.utils.MoshiUtils
import com.jacob.lib_domain.base.BaseResp
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class ResponseInterceptorNew : ResponseBodyInterceptor() {
    override fun intercept(response: Response, url: String, body: String): Response {
//      var bean = Gson().fromJson(body, BaseResp::class.java)
//        var temp = bean.response.data
////        var resp = bean?.response
////        val status = resp?.code
////        val message = resp?.msg
////        if(resp?.data != null ){
//////            if(0 != status && !message.isNullOrEmpty()){
//////                throw ApiException(status, message ?: "",null)
//////            }
////        }
////        var str = Gson().toJson(resp)
//        var bodyStr = MoshiUtils.toJson(temp)
        val responseBody = body?.toResponseBody()
        var tempResp = response.newBuilder().body(responseBody).build()

        val contentType = responseBody.contentType()
        val charset: Charset =
            contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
        Log.e(
            "ResponseInterceptorNew",
            "---esponse.body->" + response.body?.source()?.buffer?.clone()?.readString(charset)
        )
        Log.e("ResponseInterceptorNew", "---body->" + body)
        Log.e(
            "ResponseInterceptorNew",
            "---tempResp->" + tempResp.body?.source()?.buffer?.clone()?.readString(charset)
        )
        return tempResp
    }
}