package com.jacob.lib_domain.base

import com.squareup.moshi.JsonClass

/**
 * 返回数据 基础结构体
 */
@JsonClass(generateAdapter = true)
data class BaseResponse<out T> constructor(
    val code: Int = 0,
    val msg: String? = "",
    val data: T,
)
