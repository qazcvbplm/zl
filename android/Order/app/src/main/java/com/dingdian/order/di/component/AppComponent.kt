package com.dingdian.order.di.component

import android.app.Application
import android.content.Context
import com.dingdian.order.di.module.*
import com.dingdian.order.ui.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, HttpModule::class, ActivityModule::class, FragmentModule::class,
    ViewModelFactoryModule::class, AndroidInjectionModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<App> {

    override fun inject(app: App)

    fun getApplication(): Application

    fun getContext(): Context

//    fun localUser(): LocalUser

}