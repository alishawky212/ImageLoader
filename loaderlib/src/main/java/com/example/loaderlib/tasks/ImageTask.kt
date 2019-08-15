package com.example.loaderlib.tasks

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import com.example.loaderlib.memorycacheing.CacheRepo
import com.example.loaderlib.mappers.BytesMapper
import com.example.loaderlib.remoterepo.RemoteRepo
import com.example.mindloader.core.mappers.BitmapMapper
import java.net.HttpURLConnection
import java.net.URL

class ImageTask (private val url: String,
                 private val imageView: ImageView,
                 private val remoteRepo: RemoteRepo
) : Task<Bitmap?>() {

    private val uiHandler = Handler(Looper.getMainLooper())

    override fun download(url: String): Bitmap? {
       var bitmap: Bitmap? = null
       val response =  remoteRepo.getRemoteStream(url)
        response?.let {
            bitmap = BitmapMapper().mapData(it)
        }
        return bitmap
    }

    override fun call(): Bitmap? {
        val bitmap = download(url)
        bitmap?.let {
            if (imageView.tag == url) {
                updateImageView(imageView, it)
            }
        }
        return bitmap
    }

    private fun updateImageView(imageView: ImageView,bitmap: Bitmap){
        uiHandler.post {
            imageView.setImageBitmap(bitmap)
        }
    }
}