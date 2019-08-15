package com.example.loaderlib.mappers

import com.example.mindloader.core.mappers.DataStreamMapper
import java.io.ByteArrayOutputStream
import java.io.InputStream

class BytesMapper : DataStreamMapper<InputStream, ByteArray> {
    override fun mapData(input: InputStream): ByteArray {
        val outPut = ByteArrayOutputStream()
        input.use {
            outPut.use {
                input.copyTo(outPut)
            }
        }
        return outPut.toByteArray()
    }
}