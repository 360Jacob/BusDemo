package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomePageDataWrapperVo(
    var appBizTypeList: MutableList<AppBizTypeVo?>?,

    var noticeList: MutableList<AppBizTypeVo?>?,

    var bgAdList: MutableList<OpeAdvertisingVo?>?,

    var adInMinePage: OpeAdvertisingVo?,

    var initParam: Map<String?, String?>?,
    var qrCodeErrCorrectLv: Int,

    ) : BaseBean()
