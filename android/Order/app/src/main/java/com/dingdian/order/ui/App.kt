package com.dingdian.order.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.dhh.websocket.Config
import com.dhh.websocket.RxWebSocket
import com.dingdian.order.BuildConfig
import com.dingdian.order.R
import com.dingdian.order.comm.Constants
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.di.component.AppComponent
import com.dingdian.order.di.component.DaggerAppComponent
import com.dingdian.order.di.module.AppModule
import com.dingdian.order.ui.widget.printutil.AppInfo
import com.dingdian.order.utils.HttpsUtils
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.header.waveswipe.DropBounceInterpolator
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.tencent.bugly.crashreport.CrashReport
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    companion object {
        var appComponent: AppComponent? = null
        var watcher: RefWatcher? = null
    }

    @Inject
    @JvmField
    var injector: DispatchingAndroidInjector<Activity>? = null

    override fun onCreate() {
        super.onCreate()
        AppInfo.init(applicationContext)
        Utils.init(this)
        initLogConfigs()
        configUnits()

        if (BuildConfig.DEBUG) {
            ARouter.openLog()//打开日志
            ARouter.openDebug()//打开调式模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        CrashReport.initCrashReport(this, Constants.buglyId, BuildConfig.DEBUG)
        ARouter.init(this)

        watcher = LeakCanary.install(this)

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        appComponent!!.inject(this)
        LocalUser.initData()
        initSmartRefreshLayout()
        //领导要求去掉https认证
        /*HttpsUtils.getSslSocketFactory(
                arrayOf(Buffer().writeUtf8(HttpsUtils.getCer()).inputStream()),
                Buffer().writeUtf8(HttpsUtils.getBks()).inputStream(),
                HttpsUtils.password)*/
        HttpsUtils.getSslSocketFactory(
                null,
                null,
                null)

        val config = Config.Builder()
                .setShowLog(true)           //show  log
                .setShowLog(true, "your logTag")
                .setReconnectInterval(2, TimeUnit.SECONDS)  //set reconnect interval
                .build()
        RxWebSocket.setConfig(config)
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return injector!!
    }

    private fun initLogConfigs() {
        val config = LogUtils.getConfig()
        config.setBorderSwitch(false)
                .setGlobalTag("Order_Tag")
                .setLog2FileSwitch(false)
                .setLogSwitch(BuildConfig.DEBUG)
    }

    /**
     * 使用mm单位进行适配，不支持dp
     */
    private fun configUnits() {
        AutoSizeConfig.getInstance()
                .unitsManager
                .setSupportDP(false)
                .setSupportSP(false)
                .supportSubunits = Subunits.MM
    }

    private fun initSmartRefreshLayout() {
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
            layout.setReboundDuration(1000)
            layout.setReboundInterpolator(DropBounceInterpolator())
            layout.setFooterHeight(100f)
            layout.setDisableContentWhenLoading(false)
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
        }

        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
            layout.setEnableHeaderTranslationContent(false)
            MaterialHeader(context)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}