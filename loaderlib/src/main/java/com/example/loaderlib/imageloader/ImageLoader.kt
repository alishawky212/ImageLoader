package com.example.loaderlib.imageloader

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.loaderlib.MindLoader
import com.example.loaderlib.memorycacheing.CacheRepoImp
import com.example.loaderlib.remoterepo.RemoteRepoImp
import com.example.loaderlib.tasks.ImageTask
import com.example.loaderlib.tasks.Task
import com.example.mindloader.core.mappers.BitmapMapper
import java.util.concurrent.Future

class ImageLoader {
    private val cacheRepo = CacheRepoImp()
    private val remoteRepo = RemoteRepoImp(cacheRepo)
    private val executor = MindLoader.executor
    private val mRunningServices : HashMap<ImageView,Future<Bitmap?>> = hashMapOf()


    fun loadImage(url:String,imageView: ImageView,placeHolder:Int){
        cacheRepo.get(url)?.let {
            imageView.setImageBitmap(BitmapMapper().mapData(it))
            return
        } ?: run{
            imageView.tag = url
            if (placeHolder != null)
                imageView.setImageResource(placeHolder)
            createDownloadImageTask(imageView,
                ImageTask(url = url, remoteRepo = remoteRepo, imageView = imageView)
            )
        }
    }

    private fun createDownloadImageTask(imageView: ImageView , task: Task<Bitmap?>){
        mRunningServices[imageView] = executor.submit(task)
    }

    fun clearCached(){
        cacheRepo.clear()
    }

    fun cancelTask(imageView: ImageView){
        synchronized(this){
            mRunningServices.forEach{
                if (it.key == imageView && !it.value.isDone){
                    it.value.cancel(true)
                }
            }
        }
    }

    fun cancelAll(){
        synchronized(this){
            mRunningServices.forEach {
                if (!it.value.isDone){
                    it.value.cancel(true)
                }
                mRunningServices.clear()
            }
        }
    }

//    companion object {
//        private var INSTANCE: ImageLoader? = null
//        @Synchronized
//        fun getInstance(): ImageLoader {
//            return INSTANCE?.let { return it }
//                ?: run {
//                    INSTANCE = ImageLoader()
//                    return INSTANCE as ImageLoader
//                }
//        }
//    }
}