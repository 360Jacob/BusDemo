package com.jacob.lib_domain.entity

import com.jacob.lib_domain.base.BaseBean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfoVo constructor(
    @Json(name = "name")
    var name: String?,
    @Json(name = "mobileNo")
    var mobileNo: String
) : BaseBean()