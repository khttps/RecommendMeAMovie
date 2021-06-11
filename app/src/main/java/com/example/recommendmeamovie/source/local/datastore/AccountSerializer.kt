package com.example.recommendmeamovie.source.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.recommendmeamovie.Account
import com.example.recommendmeamovie.ProtoAccount
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object AccountSerializer: Serializer<ProtoAccount> {
    override val defaultValue: ProtoAccount = ProtoAccount.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoAccount {
        try {
            return ProtoAccount.parseFrom(input)

        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ProtoAccount, output: OutputStream) = t.writeTo(output)
}