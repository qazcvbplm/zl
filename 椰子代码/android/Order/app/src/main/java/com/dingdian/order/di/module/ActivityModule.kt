package com.dingdian.order.di.module

import com.dingdian.order.di.component.BaseActivityComponent
import com.dingdian.order.ui.comment.CommentActivity
import com.dingdian.order.ui.goods.EditActivity
import com.dingdian.order.ui.goods.GoodsActivity
import com.dingdian.order.ui.login.LoginActivity
import com.dingdian.order.ui.main.MainActivity
import com.dingdian.order.ui.shop.SettingActivity
import com.dingdian.order.ui.shop.TxActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(subcomponents = [BaseActivityComponent::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributesTxActivity(): TxActivity

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesCommentActivity(): CommentActivity

    @ContributesAndroidInjector
    abstract fun contributesGoodsActivity(): GoodsActivity

    @ContributesAndroidInjector
    abstract fun contributesSettingActivity(): SettingActivity

    @ContributesAndroidInjector
    abstract fun contributesEditActivity(): EditActivity

}