package com.example.loaderlib.mappers

import com.example.mindloader.core.mappers.DataStreamMapper
import org.json.JSONArray

class JSONArrayMapper : DataStreamMapper<ByteArray, JSONArray> {
    override fun mapData(input: ByteArray): JSONArray {
        return JSONArray(String(input))
    }
}