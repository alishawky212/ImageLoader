package com.example.imageloader.di.module

import androidx.lifecycle.ViewModel
import com.example.imageloader.di.ViewModelKey
import com.example.imageloader.viewmodel.MainViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMasterViewModel(mainViewModel: MainViewModel): ViewModel

}