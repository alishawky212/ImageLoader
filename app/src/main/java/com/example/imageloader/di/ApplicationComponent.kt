package com.example.imageloader.di

import com.example.imageloader.PintesterApp
import com.example.imageloader.di.builder.ActivityBuilder
import com.example.imageloader.di.module.RepositoryModule
import com.example.imageloader.di.module.SchedulersModule
import com.example.imageloader.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, RepositoryModule::class, SchedulersModule::class, ViewModelModule::class, ActivityBuilder::class])
interface ApplicationComponent {
    fun inject(app: PintesterApp)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PintesterApp): Builder

        fun build(): ApplicationComponent
    }
}