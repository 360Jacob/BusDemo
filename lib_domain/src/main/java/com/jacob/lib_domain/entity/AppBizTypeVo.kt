package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppBizTypeVo(
    @Json(name = "id")
    var id: String,
    @Json(name = "bizTypeName")
    var bizTypeName: String,
    @Json(name = "bizType")
    var bizType: Int,
    @Json(name = "bizTypeDesc")
    var bizTypeDesc: String,
    @Json(name = "displayType")
    var displayType: Int,
    @Json(name = "moreMenu")
    var moreMenu: Int,
    @Json(name = "menuList")
    var menuList: MutableList<AppMenuVo>,
    @Json(name = "secondaryPageTitleDesc")
    var secondaryPageTitleDesc: String,
    @Json(name = "secondaryPageTitleBg")
    var secondaryPageTitleBg: String,


    ) : BaseBean()

