package com.jacob.lib_data_service.remote.interceptor

import com.jacob.lib_data_service.error.mapper.ErrorManager
import com.jacob.lib_data_service.error.mapper.ErrorMapper
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

abstract class ResponseBodyInterceptor : Interceptor {
    companion object {
        val TAG: String = this::class.java.simpleName
    }

    val errorManager: ErrorManager = ErrorManager(ErrorMapper())
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        val response = chain.proceed(request)
        response.body?.let { responseBody ->
            val contentLength = responseBody.contentLength()
            val source = responseBody.source()
            source.request(Long.MAX_VALUE)
            var buffer = source.buffer

            if ("gzip".equals(response.headers["Content-Encoding"], ignoreCase = true)) {
                GzipSource(buffer.clone()).use { gzippedResponseBody ->
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                }
            }

            val contentType = responseBody.contentType()
            val charset: Charset =
                contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
            if (contentLength != 0L) {
                return intercept(response, url, buffer.clone().readString(charset))
            }
        }
        return response
    }

    abstract fun intercept(response: Response, url: String, body: String): Response
}
