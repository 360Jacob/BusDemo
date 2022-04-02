package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpeAdvertisingVo(
    var opeAdveid: String,
    var classType: Int,
    var advType: Int,
    var advTitle: String,
    var contents: String,
    var picUrl: String,
    var toUrl: String,
    var topFlag: String,
    var orderId: Int,
) : BaseBean()

