package com.example.loaderlib.remoterepo

import android.util.Log
import com.example.loaderlib.mappers.BytesMapper
import com.example.loaderlib.memorycacheing.CacheRepo
import java.net.HttpURLConnection
import java.net.URL

class RemoteRepoImp(private val cache:CacheRepo) : RemoteRepo {
    override fun getRemoteStream(url:String):ByteArray? {
        var conn: HttpURLConnection? = null
        var inputStream : ByteArray? = null
        try {
            val imageUrl = URL(url)
            conn = imageUrl.openConnection() as HttpURLConnection
            inputStream = BytesMapper().mapData(conn.inputStream)
            cache.put(url,inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            conn?.disconnect()
            conn?.inputStream?.close()
        }
        return inputStream
    }
}