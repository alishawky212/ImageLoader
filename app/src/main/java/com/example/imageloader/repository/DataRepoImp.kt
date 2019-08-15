package com.example.imageloader.repository

import com.example.imageloader.models.Item
import com.example.loaderlib.MindLoader
import com.example.mindloader.core.mappers.DataStreamMapper
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject

class DataRepoImp @Inject constructor(): DataRepo {
    override fun getItemsList(): Single<List<Item>>{
        return Single.create {
            MindLoader.getDataLoader().loadGeneric(" http://pastebin.com/raw/wgkJgazE",object :DataStreamMapper<ByteArray,List<Item>>{
                override fun mapData(input: ByteArray): List<Item> {
                    return Gson().fromJson(String(input),
                        Array<Item>::class.java).toList()
                }

            }) { result, exception ->
                if (result != null)
                    it.onSuccess(result)
                else if (exception != null)
                    it.onError(exception)
            }
        }
    }
}