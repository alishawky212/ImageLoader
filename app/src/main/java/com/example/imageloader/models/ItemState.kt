package com.example.imageloader.models

sealed class ItemState<out T> {
    object LoadingState : ItemState<Nothing>()
    data class DataState<out T>(val data: List<T>) : ItemState<T>()
    data class ErrorState<out T>(val error: String) : ItemState<T>()
}