package com.jacob.lib_base_module.api

import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.compose.ui.text.toLowerCase
import com.google.gson.Gson
import com.jacob.lib_base.BaseApplication
import com.jacob.lib_base.utils.TimeUtils
import com.jacob.lib_common.utils.SdkVersionUtil
import com.jacob.lib_common.utils.digest.DigestUtil
import com.jacob.lib_log.KLog
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.format
import java.io.UnsupportedEncodingException
import java.lang.StringBuilder
import java.net.URLEncoder
import java.util.*

class HeaderInterceptor : Interceptor {
    var TAG = this.javaClass.name
    var CLIENT_KEY_APP: String = "APP00001"


    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var httpUrl = request.url
        var paramNames = httpUrl.queryParameterNames

        val timeStamp: String? = TimeUtils.getCurrentTimeInString()
        val appVersion: String =
            SdkVersionUtil.getAppVersion(BaseApplication.getContext()).toString()
        var deviceId: String = SdkVersionUtil.getDeviceId(BaseApplication.getContext()).toString()
        if (deviceId.isNullOrEmpty()) {
            deviceId = ""
        }

//        val instance: UserInfo = UserInfo.getInstance()
//        var token: String = instance.token
        var token: String = ""
        var version = "1.0"
        var map: TreeMap<String, String> = TreeMap<String, String>()
        paramNames.forEach {
            var value = httpUrl.queryParameter(it)
            if (value == null || value == "null") {
                value = ""
            }
            map[it] = value.toString()
        }

        var biz_content: String? = ""
        if (!map.isEmpty()) {
            biz_content = Gson().toJson(map)
        }
        if (TextUtils.isEmpty(biz_content)) {
            biz_content = "no params"
        }

        val base64source = StringBuilder()

        base64source.append("app_id")
        base64source.append("=")
        base64source.append(CLIENT_KEY_APP)
        base64source.append("&")

        base64source.append("biz_content")
        base64source.append("=")
        base64source.append(
            getValueEncoded(biz_content)
        )
        base64source.append("&")

        base64source.append("sign_type")
        base64source.append("=")
        base64source.append("MD5-SHA1")
        base64source.append("&")

        base64source.append("timestamp")
        base64source.append("=")
        base64source.append(timeStamp)
        base64source.append("&")

        base64source.append("version")
        base64source.append("=")
        base64source.append(version)

        KLog.e(TAG, base64source.toString())

        val md5Str: String = DigestUtil.md5(base64source.toString())

        KLog.e(TAG, "intercept: step1=$md5Str")
        val sha1Str: String = DigestUtil.sha1(md5Str.toLowerCase() + CLIENT_KEY_APP)

        KLog.e(TAG, "intercept: step2=$sha1Str")
        val sign = sha1Str.toLowerCase()
        KLog.e(TAG, "intercept: sign=$sign")

        var newBuilder = request.newBuilder()
        newBuilder.addHeader("app_id", CLIENT_KEY_APP)
        newBuilder.addHeader("sign_type", "MD5-SHA1")
        newBuilder.addHeader("sign", sign)
        timeStamp?.let { newBuilder.addHeader("timeStamp", it) }
        newBuilder.addHeader("version", "1.0")
        getValueEncoded(biz_content)?.let { newBuilder.addHeader("biz_content", it) }

        newBuilder.addHeader("token", token)
        newBuilder.addHeader("client-type", "android")
        newBuilder.addHeader("client-id", "1")
        newBuilder.addHeader("device-id", deviceId)
        newBuilder.addHeader("app-version", appVersion)
//        newBuilder.addHeader("Accept-Encoding", "gzip")

        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            newBuilder.addHeader("Connection", "close")
        }
        val newRequest: Request = newBuilder.build()
        return chain.proceed(newRequest)
    }

    //由于okhttp header 中的 value 不支持 null, \n 和 中文这样的特殊字符,所以这里
    //会首先替换 \n ,然后使用 okhttp 的校验方式,校验不通过的话,就返回 encode 后的字符串
    private fun getValueEncoded(value: String?): String? {
        if (value == null) return ""
        val newValue = value.replace("\n", "")
        var i = 0
        val length = newValue.length
        while (i < length) {
            val c = newValue[i]
            if (c <= '\u001f' && c != '\t' || c >= '\u007f') {
                try {
                    return URLEncoder.encode(newValue, "UTF-8")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }
            }
            i++
        }
        checkNameValue(newValue)
        return newValue
    }

    private fun checkNameValue(value: String) {
        var i = 0
        val length = value.length
        while (i < length) {
            val c = value[i]
            require(!(c <= '\u001f' && c != '\t' || c >= '\u007f')) {
                format(
                    "Unexpected char %#04x at %d in %s value: %s", c.toInt(), i, "这里有中文--》", value
                )
            }
            i++
        }
    }
}