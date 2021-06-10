package com.example.recommendmeamovie.source.local.database

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converter {
    @TypeConverter
    fun toString(map: Map<String, String>): String {
        return Json.encodeToString(map)
    }

    @TypeConverter
    fun fromString(stringMap: String): Map<String,String> {
        return Json.decodeFromString(stringMap)
    }
}