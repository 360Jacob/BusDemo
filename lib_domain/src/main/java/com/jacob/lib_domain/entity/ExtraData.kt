package com.jacob.lib_domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExtraData(
    @Json(name = "userName")
    var userName: String?,

    @Json(name = "path")
    var path: String?,

    @Json(name = "miniprogramType")
    var miniprogramType: Int?,
    @Json(name = "authFlag")
    var authFlag: Int?,

    @Json(name = "permission")
    var permission: String?,

    @Json(name = "serviceType")
    var serviceType: String?,
    @Json(name = "intro")
    var intro: String?,
    @Json(name = "param")
    var param: String?,
)
