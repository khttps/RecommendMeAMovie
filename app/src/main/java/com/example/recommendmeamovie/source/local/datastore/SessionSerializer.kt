package com.example.recommendmeamovie.source.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.recommendmeamovie.SessionPreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object SessionSerializer : Serializer<SessionPreferences> {

    override val defaultValue: SessionPreferences = SessionPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SessionPreferences {
        try {
            return SessionPreferences.parseFrom(input)

        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SessionPreferences, output: OutputStream) = t.writeTo(output)
}