package com.android.chaldhal.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "first")
    var first: String?,
    @Json(name = "last")
    var last: String?,
    @Json(name = "title")
    var title: String?
)