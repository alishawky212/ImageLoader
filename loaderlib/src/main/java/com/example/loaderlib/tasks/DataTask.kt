package com.example.loaderlib.tasks

import com.example.loaderlib.remoterepo.RemoteRepo

class DataTask (private val url:String,
                private val remoteRepo: RemoteRepo,
                private val callback: (ByteArray?, Throwable?) -> Unit
) : Task<ByteArray?>() {
    override fun download(url: String): ByteArray? {
        var byteArray : ByteArray? = null
        val response = remoteRepo.getRemoteStream(url)
        return response
    }

    override fun call(): ByteArray? {
        val result = download(url)
        result?.let {
            callback(it,null)
        }
        return result
    }

}