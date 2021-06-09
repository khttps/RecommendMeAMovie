package com.example.recommendmeamovie.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkSession(
    val success: Boolean,
    @Json(name = "session_id") val sessionId: String
)

@JsonClass(generateAdapter = true)
data class NetworkToken(
    val success: Boolean,
    @Json(name = "request_token") val requestToken: String
)