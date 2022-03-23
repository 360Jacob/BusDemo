package com.jacob.lib_domain.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 返回数据 基础结构体
 */
@JsonClass(generateAdapter = true)
class BaseResp<out T> constructor(
    @Json(name = "response")
    val response: T? = null
)
