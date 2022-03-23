package com.jacob.lib_common.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import com.jacob.lib_common.utils.digest.DigestUtil

/**
 * 版本相关
 */
object SdkVersionUtil {

    /**
     * hasForyo
     *
     * @return true false
     */
    fun hasFroyo(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO
    }

    /**
     * hasGingerbread
     *
     * @return true false
     */
    fun hasGingerbread(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD
    }

    /**
     * hasHoneycomb
     *
     * @return true false
     */
    fun hasHoneycomb(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
    }

    /**
     * hasHoneycombMR1
     *
     * @return true false
     */
    fun hasHoneycombMR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1
    }

    /**
     * hasHoneycombMR2
     *
     * @return true false
     */
    fun hasHoneycombMR2(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2
    }

    /**
     * hasIceCreamSandwich
     *
     * @return true false
     */
    fun hasIceCreamSandwich(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
    }

    /**
     * hasJellyBean
     *
     * @return true false
     */
    fun hasJellyBean(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
    }

    /**
     * 4.2以上
     *
     * @return true false
     */
    fun aboveJellyBean(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN
    }

    fun getAppVersion(context: Context): Int {
        var version = 0
        try {
            version = context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return version
    }

    fun getDeviceId(context: Context?): String? {
        //TODO 判断是否有权限phoneState 有则返回deviceId,否则返回为空
        var deviceId: String = SPUtils.get(context!!, "device-id", "") as String
        return if (deviceId.isNullOrEmpty()) {
            deviceId = DigestUtil.md5("" + getBaseDeviceId(context))
            SPUtils.put(context, "device-id", deviceId)
            deviceId
        } else {
            deviceId
        }
    }

    fun getBaseDeviceId(context: Context?): String? {

        val androidId =
            Settings.Secure.getString(context!!.contentResolver, Settings.Secure.ANDROID_ID)
        if (!androidId.isNullOrEmpty()) {
            return androidId
        }
        val serial = Build.SERIAL
        return if (!serial.isNullOrEmpty()) {
            serial
        } else getPseudo()

    }

    private fun getPseudo(): String? {
        return "35" + "/" + //we make this look like a valid IMEI
                Build.BOARD.length % 10 + "/" + Build.BRAND.length % 10 + "/" + Build.CPU_ABI.length % 10 + "/" + Build.DEVICE.length % 10 + "/" + Build.DISPLAY.length % 10 + "/" + Build.HOST.length % 10 + "/" + Build.ID.length % 10 + "/" + Build.MANUFACTURER.length % 10 + "/" + Build.MODEL.length % 10 + "/" + Build.PRODUCT.length % 10 + "/" + Build.TAGS.length % 10 + "/" + Build.TYPE.length % 10 + "/" + Build.USER.length % 10 + "/" //13 digits
    }
}