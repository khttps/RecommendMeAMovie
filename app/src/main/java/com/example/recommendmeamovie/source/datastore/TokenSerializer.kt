package com.example.recommendmeamovie.source.datastore

import androidx.datastore.core.Serializer
import com.example.recommendmeamovie.Token
import com.google.protobuf.InvalidProtocolBufferException
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class TokenSerializer(override val defaultValue: Token) : Serializer<Token> {
    override suspend fun readFrom(input: InputStream): Token {
        try {
            return Token.parseFrom(input)

        } catch (exception: InvalidProtocolBufferException) {
            throw Throwable("Cannot read proto. ", exception)
        }
    }

    override suspend fun writeTo(t: Token, output: OutputStream) = t.writeTo(output)


}