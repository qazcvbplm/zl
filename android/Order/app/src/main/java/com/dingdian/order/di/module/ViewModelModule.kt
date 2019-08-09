package com.dingdian.order.di.module

import android.arch.lifecycle.ViewModel
import com.dingdian.order.di.scope.ViewModelKey
import com.dingdian.order.ui.comment.CommentViewModel
import com.dingdian.order.ui.goods.EditViewModel
import com.dingdian.order.ui.goods.GoodsViewModel
import com.dingdian.order.ui.login.LoginViewModel
import com.dingdian.order.ui.main.MainViewModel
import com.dingdian.order.ui.main.mine.MineFragment
import com.dingdian.order.ui.main.mine.MineViewModel
import com.dingdian.order.ui.main.order.OrderViewModel
import com.dingdian.order.ui.main.order.complete.CompleteViewModel
import com.dingdian.order.ui.main.order.received.ReceivedViewModel
import com.dingdian.order.ui.main.order.unreceive.UnReceiveViewModel
import com.dingdian.order.ui.shop.SettingViewModel
import com.dingdian.order.ui.shop.TxViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModule(viewModel: MainViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModule(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TxViewModel::class)
    internal abstract fun bindTxViewModule(viewModel: TxViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    internal abstract fun bindOrderViewModule(viewModel: OrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MineViewModel::class)
    internal abstract fun bindMineViewModule(viewModel: MineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentViewModel::class)
    internal abstract fun bindCommentViewModule(viewModel: CommentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GoodsViewModel::class)
    internal abstract fun bindGoodsViewModule(viewModel: GoodsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    internal abstract fun bindSettingViewModule(viewModel: SettingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnReceiveViewModel::class)
    internal abstract fun bindUnReceiveViewModule(viewModel: UnReceiveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReceivedViewModel::class)
    internal abstract fun bindReceivedViewModule(viewModel: ReceivedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompleteViewModel::class)
    internal abstract fun bindCompleteViewModule(viewModel: CompleteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditViewModel::class)
    internal abstract fun bindEditViewModule(viewModel: EditViewModel): ViewModel


}