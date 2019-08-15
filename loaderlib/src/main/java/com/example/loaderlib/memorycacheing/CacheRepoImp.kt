package com.example.loaderlib.memorycacheing

class CacheRepoImp : CacheRepo {

    private val memoryCache = MemoryCache()

    override fun put(url: String, bitmap: ByteArray) {
        memoryCache.put(url, bitmap)
    }

    override fun get(url: String): ByteArray? {
        return memoryCache.get(url)
    }

    override fun clear() {
        memoryCache.clear()
    }
}