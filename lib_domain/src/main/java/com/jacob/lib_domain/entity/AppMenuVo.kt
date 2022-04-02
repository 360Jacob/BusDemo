package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppMenuVo(
    var id: String?,

    var bizName: String?,

    var bizCode: String?,

    var bizIcon: String?,

    var redirectType: Int,

    var bizType: Int,

    var redirectUrl: String,

    var orderBy: Int,

    var homeOrderBy: Int,

    var extraData: String,

    var jsBridgeObject: String,

    var jsMethods: MutableList<String>,

    ) : BaseBean(), Comparable<AppMenuVo> {
    override fun compareTo(other: AppMenuVo): Int {
        return orderBy - other.orderBy
    }
}


