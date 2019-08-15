package com.example.loaderlib

import com.example.loaderlib.dataloader.DataLoader
import com.example.loaderlib.imageloader.ImageLoader
import java.util.concurrent.Executors

object MindLoader {
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    private val cacheSize = maxMemory / 4

    var defaultCacheConfigurations: Int = cacheSize
        private set

    val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    private val dataLoader = DataLoader()

    private val imageLoader = ImageLoader()

    fun getDataLoader():DataLoader{
        return dataLoader
    }

    fun getImageLoader():ImageLoader{
        return imageLoader
    }

    fun init(cacheSize: Int) {
        this.defaultCacheConfigurations = cacheSize
    }
}