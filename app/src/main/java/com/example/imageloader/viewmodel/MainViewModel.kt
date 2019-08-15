package com.example.imageloader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageloader.di.module.IO_SCHEDULER
import com.example.imageloader.models.Item
import com.example.imageloader.models.ItemState
import com.example.imageloader.usecase.MainUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    private val useCase: MainUseCase,
    @Named(value = IO_SCHEDULER) private val ioScheduler: Scheduler
): ViewModel() {

    private val items = MutableLiveData<ItemState<Item>>()
    private val compositeDisposable = CompositeDisposable()

    fun getItemsList(){
        compositeDisposable.add(useCase.getRemoteData()
            .doOnSubscribe { items.postValue(ItemState.LoadingState) }
            .subscribeOn(ioScheduler)
            .observeOn(ioScheduler)
            .subscribe ({ items.postValue(ItemState.DataState(it)) },{
                items.postValue(ItemState.ErrorState(it.localizedMessage))
            })
        )
    }

    fun getItemsLiveData():LiveData<ItemState<Item>>{
        return items
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}