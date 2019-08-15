package com.example.imageloader.di.builder

import com.example.imageloader.di.scope.PerActivity
import com.example.imageloader.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}