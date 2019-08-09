package com.dingdian.order.di.module

import android.arch.lifecycle.ViewModelProvider
import com.dingdian.order.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}