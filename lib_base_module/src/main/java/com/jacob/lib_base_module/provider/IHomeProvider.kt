package com.jacob.lib_base_module.provider

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Describe:
 * 首页
 *
 */
interface IHomeProvider : IProvider {
    val mainHomeFragment: Fragment
}