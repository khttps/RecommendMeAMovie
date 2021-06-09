package com.example.recommendmeamovie.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkAccount(
    val name: String,
    val username: String,
    val avatar: Avatar
)

@JsonClass(generateAdapter = true)
data class Avatar(
    @Json(name = "tmdb") val avatarContainer: AvatarContainer
)

@JsonClass(generateAdapter = true)
data class AvatarContainer(
    @Json(name = "avatar_path") val avatarPath: String
)
