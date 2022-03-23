package com.jacob.lib_data_service.utils

import android.os.Handler
import android.os.Looper

object ThreadUtils {
    private const val LOG_TAG = "线程工具类"
    private val M_MAIN_LOOPER_HANDLER = Handler(Looper.getMainLooper())

    /**
     * 延时切换到主线程
     *
     * @param runnable Runnable
     * @param delayed  时长 Millis
     */
    fun runOnUiThread(runnable: Runnable?, delayed: Long) {
        M_MAIN_LOOPER_HANDLER.postDelayed(runnable!!, delayed)
    }

    /**
     * 切换到主线程
     *
     * @param runnable Runnable
     */
    fun runOnUiThread(runnable: Runnable?) {
        M_MAIN_LOOPER_HANDLER.post(runnable!!)
    }

    /**
     * 切换到主线程并尽可能立刻执行。
     *
     * @param runnable Runnable
     */
    fun runOnUiThreadImediatly(runnable: Runnable?) {
        M_MAIN_LOOPER_HANDLER.postAtFrontOfQueue(runnable!!)
    }

    /**
     * 切换到主线程
     *
     * @param runnable Runnable
     */
    fun runOnUiThreadDelay(runnable: Runnable?, delayMillis: Long) {
        M_MAIN_LOOPER_HANDLER.postDelayed(runnable!!, delayMillis)
    }
}