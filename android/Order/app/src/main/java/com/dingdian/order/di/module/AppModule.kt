package com.dingdian.order.di.module

import android.app.Application
import android.content.Context
import android.support.v4.util.ArrayMap
import com.dingdian.order.ui.App
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule @Inject constructor(val app: App) {

    @Singleton
    @Provides
    fun provideApp(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideBus(): EventBus = EventBus.getDefault()

    @Singleton
    @Provides
    internal fun provideExtras(): Map<String, Any> {
        return ArrayMap()
    }

}