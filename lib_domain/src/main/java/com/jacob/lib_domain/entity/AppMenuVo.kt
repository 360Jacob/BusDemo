package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppMenuVo(
    @Json(name = "id")
    var id: String?,

    @Json(name = "bizName")
    var bizName: String?,

    @Json(name = "bizCode")
    var bizCode: String?,

    @Json(name = "bizIcon")
    var bizIcon: String?,

    @Json(name = "redirectType")
    var redirectType: Int,

    @Json(name = "bizType")
    var bizType: Int,

    @Json(name = "redirectUrl")
    var redirectUrl: String,

    @Json(name = "orderBy")
    var orderBy: Int,

    @Json(name = "homeOrderBy")
    var homeOrderBy: Int,

    @Json(name = "extraData")
    var extraData: String,

    @Json(name = "jsBridgeObject")
    var jsBridgeObject: String,

    @Json(name = "jsMethods")
    var jsMethods: MutableList<String>,

    ) : BaseBean(), Comparable<AppMenuVo> {
    override fun compareTo(other: AppMenuVo): Int {
        return orderBy - other.orderBy
    }
}


