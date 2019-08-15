package com.example.imageloader.di.module

import com.example.imageloader.repository.DataRepo
import com.example.imageloader.repository.DataRepoImp
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: DataRepoImp): DataRepo

}