package com.jacob.lib_domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrivateVersionVo(
    var privacyVersion: String
)
