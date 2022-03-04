package com.jacob.demo

import com.jacob.lib_base.manager.ActivityManager
import com.jacob.lib_base_module.ModuleApplication

class MyApplication : ModuleApplication() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityManager.instance)
    }
}