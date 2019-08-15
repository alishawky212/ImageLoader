package com.example.loaderlib.memorycacheing

import android.util.LruCache
import com.example.loaderlib.MindLoader

open class MemoryCache : CacheRepo {
    private val memoryCache : LruCache<String,ByteArray>

    init {
        memoryCache = object : LruCache<String,ByteArray>(MindLoader.defaultCacheConfigurations){
            override fun sizeOf(key: String?, value: ByteArray): Int {
                return value.size/1024
            }
        }
    }

    override fun put(url: String, bitmap: ByteArray) {
        memoryCache.put(url,bitmap)
    }

    override fun get(url: String): ByteArray? {
       return memoryCache.get(url)
    }

    override fun clear() {
       memoryCache.evictAll()
    }
}