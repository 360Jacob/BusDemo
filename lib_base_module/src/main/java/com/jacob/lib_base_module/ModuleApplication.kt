package com.jacob.lib_base_module

import com.jacob.lib_base.BaseApplication
import com.jacob.lib_base_module.api.GzipRequestInterceptor
import com.jacob.lib_base_module.api.HeaderInterceptor
import com.jacob.lib_common.URL_BASE
import com.jacob.lib_data_service.config.NetConfig


/**
 * 初始化应用程序
 */
open class ModuleApplication : BaseApplication() {

    override fun initOnlyMainProcessInLowPriorityThread() {
        super.initOnlyMainProcessInLowPriorityThread()
        initNet()
    }

    private fun initNet() {
//        var url = "https://demoapp.xmparking.net/"+"merchant_bus_web_app/bus/"
        var url = "https://demoapp.xmparking.net/merchant_bus_web_app/bus/"
        val config = NetConfig.Builder()
            .setBaseUrl(url)
            .addInterceptor(HeaderInterceptor())
//            .addInterceptor(GzipRequestInterceptor())
            .build()
        config.initContext(this)
    }
}
