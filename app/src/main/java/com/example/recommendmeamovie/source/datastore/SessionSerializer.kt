package com.example.recommendmeamovie.source.datastore

import androidx.datastore.core.Serializer
import com.example.recommendmeamovie.Session
import com.google.protobuf.InvalidProtocolBufferException
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class SessionSerializer(override val defaultValue: Session) : Serializer<Session> {
    override suspend fun readFrom(input: InputStream): Session {
        try {
            return Session.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw Throwable("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Session, output: OutputStream) = t.writeTo(output)
}