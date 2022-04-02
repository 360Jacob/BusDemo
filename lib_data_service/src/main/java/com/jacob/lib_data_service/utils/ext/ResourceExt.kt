package com.jacob.lib_data_service.utils.ext

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_log.KLog

/**
 * Describe:
 * 结果包装类拓展
 *
 */


/**
 * 快捷拓展获取到相应的数据结果
 * @loading 加载中回调
 * @fail 失败回调
 * @success 成功回调
 */
fun <T> Resource<T>.launch(
    loading: (() -> Unit)? = null,
    fail: ((code: Int?) -> Unit)? = null,
    success: (data: T?) -> Unit
) {
    when (this) {
        is Resource.Success -> success(data)
        is Resource.Loading -> loading?.invoke()
        else -> {
            this.code.let { KLog.e("Resource", "--------->$it") }
            fail?.let { it(code) }
        }
    }
}