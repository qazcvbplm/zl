package com.dingdian.order.di.module

import com.dingdian.order.di.component.BaseFragmentComponent
import com.dingdian.order.ui.main.mine.MineFragment
import com.dingdian.order.ui.main.order.OrderFragment
import com.dingdian.order.ui.main.order.complete.CompleteFragment
import com.dingdian.order.ui.main.order.received.ReceivedFragment
import com.dingdian.order.ui.main.order.unreceive.UnReceiveFragment
import com.dingdian.order.ui.main.order.unreceive.UnReceiveViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [BaseFragmentComponent::class])
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesMineFragment(): MineFragment

    @ContributesAndroidInjector
    abstract fun contributesOrderFragment(): OrderFragment

    @ContributesAndroidInjector
    abstract fun contributesReceivedFragment(): ReceivedFragment

    @ContributesAndroidInjector
    abstract fun contributesUnReceiveFragment(): UnReceiveFragment

    @ContributesAndroidInjector
    abstract fun contributesCompleteFragment(): CompleteFragment
}