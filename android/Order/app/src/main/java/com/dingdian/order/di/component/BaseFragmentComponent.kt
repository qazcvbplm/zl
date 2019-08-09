package com.dingdian.order.di.component

import com.dingdian.order.ui.base.BaseFragment
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Subcomponent(modules = [AndroidSupportInjectionModule::class, AndroidInjectionModule::class])
interface BaseFragmentComponent : AndroidInjector<BaseFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BaseFragment>()

}