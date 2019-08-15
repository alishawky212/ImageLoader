package com.example.loaderlib.dataloader

import com.example.loaderlib.MindLoader
import com.example.loaderlib.memorycacheing.CacheRepoImp
import com.example.loaderlib.tasks.DataTask
import com.example.loaderlib.tasks.Task
import com.example.loaderlib.mappers.JSONArrayMapper
import com.example.loaderlib.remoterepo.RemoteRepoImp
import com.example.mindloader.core.mappers.DataStreamMapper
import com.example.mindloader.core.mappers.JSONObjectMapper
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.Executors
import java.util.concurrent.Future

class DataLoader {
    private val cacheRepo = CacheRepoImp()
    private val remoteRepo = RemoteRepoImp(cacheRepo)
    private val executor = MindLoader.executor
    private val mRunningServices : HashMap<String, Future<ByteArray?>> = hashMapOf()
    private val jsonObjectMapper = JSONObjectMapper()
    private val jsonArrayMapper = JSONArrayMapper()

    fun loadJsonObject(url:String,callback: (JSONObject?, Throwable?) -> Unit){
          cacheRepo.get(url)?.let {
              callback(jsonObjectMapper.mapData(it),null)
              return@let
          } ?: run{
              val dataLoadingTask = DataTask(url,remoteRepo, jsonObjectResponse(callback))
              createTask(url,dataLoadingTask)
          }
    }

    fun loadJsonArray(url: String,callback: (JSONArray?, Throwable?) -> Unit){
         cacheRepo.get(url)?.let {
             callback(jsonArrayMapper.mapData(it),null)
         } ?: run{
             val dataLoadingTask = DataTask(url,remoteRepo, jsonArrayResponse(callback))
             createTask(url,dataLoadingTask)
         }
    }

    fun <T>loadGeneric(url: String,mapper: DataStreamMapper<ByteArray,T>,callback: (T?, Throwable?) -> Unit){
        cacheRepo.get(url)?.let {
            callback(mapper.mapData(it),null)
        } ?: run {
            val dataLoadingTask = DataTask(url,remoteRepo){result,exception ->
                if (result!=null)
                    callback(mapper.mapData(result),null)
                else
                    callback(null,exception)
            }
            createTask(url,dataLoadingTask)
        }
    }


    private fun jsonObjectResponse(callback: (JSONObject?, Throwable?) -> Unit): (ByteArray?, Throwable?) -> Unit {
        return { result, exception ->
            if (result != null)
                callback(jsonObjectMapper.mapData(result), null)
            else
                callback(null, exception)
        }
    }

    private fun jsonArrayResponse(callback: (JSONArray?, Throwable?) -> Unit): (ByteArray?, Throwable?) -> Unit {
        return { result, exception ->
            if (result != null)
                callback(jsonArrayMapper.mapData(result), null)
            else
                callback(null, exception)
        }
    }

    private fun createTask(url:String,task:Task<ByteArray?>){
        mRunningServices[url] = executor.submit(task)
    }

    fun cancelTask(url: String){
        synchronized(this){
            mRunningServices.forEach {
                if (it.key == url && !it.value.isDone){
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
}