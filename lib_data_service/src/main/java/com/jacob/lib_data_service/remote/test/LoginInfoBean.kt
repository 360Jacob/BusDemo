package com.jacob.lib_data_service.remote.test

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginInfoBean(
    @Json(name = "name")
    val name:String
)
