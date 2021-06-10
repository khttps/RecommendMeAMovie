package com.example.recommendmeamovie.source.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.recommendmeamovie.Account
import java.io.InputStream
import java.io.OutputStream

object AccountSerializer: Serializer<Account> {
    override val defaultValue: Account = Account.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Account {
        try {
            return Account.parseFrom(input)

        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Account, output: OutputStream) = t.writeTo(output)
}