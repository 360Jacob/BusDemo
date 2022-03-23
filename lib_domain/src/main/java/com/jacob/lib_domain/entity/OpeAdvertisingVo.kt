package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpeAdvertisingVo(
    @Json(name = "opeAdveid")
    var opeAdveid: String,
    @Json(name = "classType")
    var classType: Int,
    @Json(name = "advType")
    var advType: Int,
    @Json(name = "advTitle")
    var advTitle: String,
    @Json(name = "contents")
    var contents: String,
    @Json(name = "picUrl")
    var picUrl: String,
    @Json(name = "toUrl")
    var toUrl: String,
    @Json(name = "topFlag")
    var topFlag: String,
    @Json(name = "orderId")
    var orderId: Int,
) : BaseBean()

