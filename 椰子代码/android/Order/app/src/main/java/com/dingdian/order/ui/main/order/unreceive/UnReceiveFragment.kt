package com.dingdian.order.ui.main.order.unreceive

import android.arch.lifecycle.Observer
import android.bluetooth.BluetoothAdapter
import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.dhh.websocket.RxWebSocket
import com.dhh.websocket.WebSocketSubscriber
import com.dingdian.order.R
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.databinding.UnReceiveFragmentBinding
import com.dingdian.order.ui.base.MVVMFragment
import com.dingdian.order.ui.bluetooth.BluetoothController
import com.dingdian.order.ui.widget.GoodsDialog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.un_receive_fragment.*
import okhttp3.WebSocket
import okio.ByteString
import rx.Subscription
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UnReceiveFragment : MVVMFragment<UnReceiveViewModel, UnReceiveFragmentBinding>() {

    companion object {
        fun newInstance() = UnReceiveFragment()
    }

    val adapter by lazy { UnReceiveOrderAdapter() }

    var mAdapter: BluetoothAdapter? = null

    override fun createViewModel(): UnReceiveViewModel = getViewModel(UnReceiveViewModel::class.java)

    override fun getLayout(): Int = R.layout.un_receive_fragment

    override fun initView() {
        super.initView()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        refreshLayout.setColorSchemeColors(Color.parseColor("#FFFD4D4D"))
        refreshLayout.setOnRefreshListener {
            mViewModel?.unReceiveList()
        }

        mViewModel?.refreshEvent?.observe(this, Observer {
            it?.let {
                refreshLayout.isRefreshing = it
            }
        })

        mViewModel?.orderList?.observeForever {
            adapter.setNewData(it)
            it?.let { list ->
                if (LocalUser.isAutoReceive()) {//自动接单
                    mViewModel?.receiveOrder(adapter, list[0])
                }
            }
            if (it != null && it.size > 0) {
                if ((mediaDisposable == null || mediaDisposable!!.isDisposed) && !mediaPlay.isPlaying) {
                    Observable.interval(0, 5, TimeUnit.SECONDS)
                            .take(2)
                            .doOnSubscribe {
                                mediaDisposable = it
                            }
                            .doOnComplete {
                                mediaDisposable?.dispose()
                            }
                            .subscribe {
                                mediaPlay.start()
                            }
                }
            }

        }

        mViewModel?.receiveSuccess?.observeForever {
            if (LocalUser.isAutoReceive() && adapter.data.size > 0) {//自动接单
                mViewModel?.receiveOrder(adapter, adapter.data[0])
            }
        }

        adapter.callback = {
            mViewModel?.receiveOrder(adapter, it)
        }

        adapter.viewGoods = {
            GoodsDialog(it.op, context!!).show()
        }

    }

    var disposable: CompositeDisposable = CompositeDisposable()
    val mediaPlay by lazy { MediaPlayer.create(context, R.raw.blk) }
    val mediaPlayNoSound by lazy { MediaPlayer.create(context, R.raw.no_sound) }
    var mediaDisposable: Disposable? = null
    //    lateinit var subscription: Subscription
    override fun initData() {

//        mediaPlay.setDataSource(context?.assets?.openFd("blk.mp3"))
//        mediaPlay = MediaPlayer.create()
//        mediaPlay.prepareAsync()

        Observable.interval(40, TimeUnit.SECONDS)
                .doOnSubscribe {
                    disposable.add(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mViewModel?.unReceiveList()
                }, {
                    LogUtils.d(it)
                })

        mediaPlayNoSound.isLooping = true
        mediaPlayNoSound.start()

//        subscription = RxWebSocket.get("ws://www.chuyinkeji.cn:11000/websocket?id=${LocalUser.getUser().value!!.id}")
//            .observeOn(Schedulers.io())
//            .subscribe(object : WebSocketSubscriber() {
//                override fun onOpen(webSocket: WebSocket) {
//                    super.onOpen(webSocket)
//                }
//
//                override fun onReconnect() {
//                    super.onReconnect()
//                }
//
//                override fun onClose() {
//                    super.onClose()
//                }
//
//                override fun onMessage(text: String) {
//                    super.onMessage(text)
//                    LogUtils.d(text)
//                    mViewModel?.unReceiveList()
//                }
//
//                override fun onMessage(byteString: ByteString) {
//                    super.onMessage(byteString)
//                }
//
//                override fun onError(e: Throwable?) {
//                    super.onError(e)
//                }
//            })

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            BluetoothController.init(this)
        }
    }

    override fun onStart() {
        super.onStart()
        BluetoothController.init(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlay.release()
        disposable.dispose()
//        if (subscription != null && !subscription.isUnsubscribed) {
//            subscription.unsubscribe()
//        }
    }

}
