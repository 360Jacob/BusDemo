package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomePageDataWrapperVo(
    @Json(name = "appBizTypeList")
    var appBizTypeList: MutableList<AppBizTypeVo?>?,

    @Json(name = "noticeList")
    var noticeList: MutableList<AppBizTypeVo?>?,

    @Json(name = "bgAdList")
    var bgAdList: MutableList<OpeAdvertisingVo?>?,

    @Json(name = "adInMinePage")
    var adInMinePage: OpeAdvertisingVo?,

    @Json(name = "initParam")
    var initParam: Map<String?, String?>?,
    @Json(name = "qrCodeErrCorrectLv")
    var qrCodeErrCorrectLv: Int,

    ) : BaseBean()
