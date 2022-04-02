package com.jacob.lib_domain.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExtraData(
    var userName: String?,

    var path: String?,

    var miniprogramType: Int?,
    var authFlag: Int?,

    var permission: String?,

    var serviceType: String?,
    var intro: String?,
    var param: String?,
)
