package com.android.chaldhal.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dob(
    @Json(name = "age")
    var age: Int?,
    @Json(name = "date")
    var date: String?
)