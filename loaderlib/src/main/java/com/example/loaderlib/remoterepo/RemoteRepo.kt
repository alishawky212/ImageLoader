package com.example.loaderlib.remoterepo


interface RemoteRepo {
    fun getRemoteStream(url:String): ByteArray?
}