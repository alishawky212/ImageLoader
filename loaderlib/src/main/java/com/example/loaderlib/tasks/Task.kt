package com.example.loaderlib.tasks

import java.util.concurrent.Callable

abstract class Task<T> : Callable<T>{
    abstract fun download(url: String): T
}