package com.android.chaldhal.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "dob")
    var dob: Dob?,
    @Json(name = "name")
    var name: Name?
)