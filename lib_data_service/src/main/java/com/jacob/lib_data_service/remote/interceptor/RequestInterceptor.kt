package com.jacob.lib_data_service.remote.interceptor

import android.util.Log
import com.jacob.lib_data_service.config.NetConfig
import com.jacob.lib_data_service.config.contentType
import com.jacob.lib_data_service.config.contentTypeValue
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Describe:
 * <p>请求头拦截器</p>
 *
 */
class RequestInterceptor : Interceptor {


    companion object {
        val TAG: String = RequestInterceptor::class.java.simpleName
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val httpUrl = original.url.toString()


        val newRequestBuilder = original.newBuilder()
            // .removeHeader("User-Agent")
            // .addHeader("User-Agent", "dh-net-okhttp")
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)

        newRequestBuilder.cacheControl(
            CacheControl.Builder()
                //每个请求添加5分钟的缓存，这个仅仅针对服务器给了响应头中的标记的情况下
                .maxAge(5, TimeUnit.MINUTES)
                .build()
        )

        //设置请求头
        val heads = NetConfig.getHeads()
        if (heads.isNotEmpty()) {
            for (head in heads) {
                newRequestBuilder.addHeader(head.key, head.value.toString())
            }
        }

        val build = newRequestBuilder.build()
        Log.d(TAG, "intercept:${build.headers}")
        return chain.proceed(build)
    }
}