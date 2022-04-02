package com.jacob.lib_domain.base

import com.squareup.moshi.JsonClass

/**
 * 返回数据 基础结构体
 */
@JsonClass(generateAdapter = true)
data class BaseResp<out T>(
    val response: T
)
