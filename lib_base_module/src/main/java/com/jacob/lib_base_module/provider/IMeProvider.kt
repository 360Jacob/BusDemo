package com.jacob.lib_base_module.provider

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Describe:
 * 个人中心
 *
 */
interface IMeProvider : IProvider {
    val mainMeFragment: Fragment
}