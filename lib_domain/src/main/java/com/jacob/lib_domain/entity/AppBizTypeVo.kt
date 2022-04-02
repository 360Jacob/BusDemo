package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppBizTypeVo(
    var id: String,
    var bizTypeName: String,
    var bizType: Int,
    var bizTypeDesc: String,
    var displayType: Int,
    var moreMenu: Int,
    var menuList: MutableList<AppMenuVo>,
    var secondaryPageTitleDesc: String,
    var secondaryPageTitleBg: String,


    ) : BaseBean()

