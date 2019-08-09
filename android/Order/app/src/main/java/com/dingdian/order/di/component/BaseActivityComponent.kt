package com.dingdian.order.di.component

import com.dingdian.order.ui.base.BaseActivity
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Subcomponent(modules = [AndroidSupportInjectionModule::class, AndroidInjectionModule::class])
interface BaseActivityComponent : AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BaseActivity>()
}