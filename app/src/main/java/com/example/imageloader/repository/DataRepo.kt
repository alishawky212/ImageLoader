package com.example.imageloader.repository

import com.example.imageloader.models.Item
import com.example.imageloader.models.ItemState
import io.reactivex.Single

interface DataRepo {
    fun getItemsList():Single<List<Item>>
}