package com.dingdian.order.comm

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object Constants {

    //0开发环境、3线上环境
    const val environment_dev = 0
    const val environment_release = 3
    const val environment = environment_dev

    //DB constanst
    val DB_NAME = "db_fun"
    //FunDrop md5 32
    val DB_PWD = "271b9476790efe889473b90927307ea7"

    var buglyId = when (environment) {
        else -> "7b962de31c"
    }

    const val BUNDLE_KEY = "bundle_key"
    const val BEAN_KEY = "bean_key"

    var isBondBluetooth = false

    const val PAGE_SIZE = 20
    //倒计时
    const val COUNT_DOWN_DELAY = 0
    const val COUNT_DOWN_PERIOD = 1
    const val COUNT_DOWN_NUM = 60

    fun countDownTimerObservable(doOnSubscribe: (Disposable) -> Unit, doOnComplete: () -> Unit): Observable<Long> {
        //倒计时
        return Observable.interval(COUNT_DOWN_DELAY.toLong(), COUNT_DOWN_PERIOD.toLong(), TimeUnit.SECONDS)
                .take((COUNT_DOWN_NUM).toLong())
                .map<Long> { aLong -> COUNT_DOWN_NUM - aLong }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { disposable -> doOnSubscribe.invoke(disposable) }
                .doOnComplete { doOnComplete.invoke() }
    }
}