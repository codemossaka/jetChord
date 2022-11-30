package com.godsonpeya.jetchord.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Verses(
    @Json(name = "VerseType")
    var verseType: String,
    @Json(name = "Text")
    var text: String
)
