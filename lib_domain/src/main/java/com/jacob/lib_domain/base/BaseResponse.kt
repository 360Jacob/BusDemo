package com.jacob.lib_domain.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 返回数据 基础结构体
 */
@JsonClass(generateAdapter = true)
class BaseResponse<out T> constructor(
    @Json(name = "code")
    val code: Int = 0,
    @Json(name = "msg")
    val msg: String? = "",
    @Json(name = "data")
    val data: T? = null
)
