package com.android.chaldhal.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "page")
    var page: Int?,
    @Json(name = "results")
    var results: Int?,
    @Json(name = "seed")
    var seed: String?,
    @Json(name = "version")
    var version: String?
)