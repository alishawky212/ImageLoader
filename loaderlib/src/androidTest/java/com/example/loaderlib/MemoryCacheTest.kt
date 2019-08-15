package com.example.loaderlib

import com.example.loaderlib.memorycacheing.MemoryCache
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class MemoryCacheTest {

    lateinit var memoryCache: MemoryCache

    @Before
    fun setUp() {
        memoryCache = MemoryCache()
    }

    @Test
    fun put() {
        val bytes = ByteArray(10)
        memoryCache.put("test",bytes)
        assertThat(memoryCache.get("test"), `is`(bytes))
    }

    @Test
    fun clear() {
        val bytes = ByteArray(10)
        memoryCache.put("test",bytes)
        memoryCache.clear()
        assertNull(memoryCache.get("test"))
    }
}