package com.jacob.lib_common.utils.func

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Describe:
 * ARouter 跳转工具类
 *
 */



fun startActivity(path: String, bundle: Bundle?) {
    ARouter.getInstance().apply {
        build(path).with(bundle).navigation()
    }
}