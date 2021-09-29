package com.android.chaldhal.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "info")
    var info: Info?,
    @Json(name = "results")
    var results: List<Result>?
)