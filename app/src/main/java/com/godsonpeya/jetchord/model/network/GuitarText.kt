package com.godsonpeya.jetchord.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GuitarText(
    @Json(name = "Title")
    var title: String,
    @Json(name = "KeyChords")
    var keyChords: String,
    @Json(name = "Verses")
    var verses: List<Verses>
): java.io.Serializable
