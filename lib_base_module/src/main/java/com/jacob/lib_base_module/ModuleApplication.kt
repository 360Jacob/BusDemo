package com.jacob.lib_base_module

import com.jacob.lib_base.BaseApplication
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
        val config = NetConfig.Builder()
            .setBaseUrl(URL_BASE)
            .build()
        config.initContext(this)
    }
}
