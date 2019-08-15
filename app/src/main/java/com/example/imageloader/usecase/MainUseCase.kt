package com.example.imageloader.usecase

import com.example.imageloader.models.Item
import com.example.imageloader.repository.DataRepo
import io.reactivex.Single
import javax.inject.Inject

open class MainUseCase @Inject constructor(private val dataRepo:DataRepo) {
    fun getRemoteData(): Single<List<Item>> {
        return dataRepo.getItemsList()
    }
}