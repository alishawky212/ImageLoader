package com.example.loaderlib.memorycacheing

interface CacheRepo {
    fun put(url: String, bitmap: ByteArray)

    fun get(url: String): ByteArray?

    fun clear()

}