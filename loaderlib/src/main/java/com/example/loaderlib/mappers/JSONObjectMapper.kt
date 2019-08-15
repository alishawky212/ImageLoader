package com.example.mindloader.core.mappers

import org.json.JSONObject

class JSONObjectMapper : DataStreamMapper<ByteArray,JSONObject> {
    override fun mapData(input: ByteArray): JSONObject {
        return JSONObject(String(input))
    }
}