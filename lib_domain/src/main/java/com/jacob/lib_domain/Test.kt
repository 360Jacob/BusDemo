package com.jacob.lib_domain

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Demo constructor(
    @Json(name = "name") var name: String
) : BaseBean()