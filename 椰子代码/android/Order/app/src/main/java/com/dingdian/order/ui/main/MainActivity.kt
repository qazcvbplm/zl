package com.dingdian.order.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.dingdian.order.R
import com.dingdian.order.api.download.DownloadListener
import com.dingdian.order.api.download.DownloadUtil
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.Constants
import com.dingdian.order.databinding.ActivityMainBinding
import com.dingdian.order.ui.base.MVVMActivity
import com.dingdian.order.ui.main.mine.MineFragment
import com.dingdian.order.ui.main.order.OrderFragment
import com.dingdian.order.ui.widget.GeneralDialog
import com.dingdian.order.ui.widget.bt.BtInterface
import com.dingdian.order.ui.widget.bt.BtUtil
import com.dingdian.order.ui.widget.print.PrintMsgEvent
import com.dingdian.order.ui.widget.print.PrintUtil
import com.dingdian.order.ui.widget.print.PrinterMsgType
import com.gyf.barlibrary.ImmersionBar
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe

@Route(path = ArouterPath.main)
class MainActivity : MVVMActivity<MainViewModel, ActivityMainBinding>(), BtInterface {

    private val rxPermissions: RxPermissions by lazy { RxPermissions(this) }

    private lateinit var updateDialog: GeneralDialog

    private lateinit var mDownloadUtil: DownloadUtil
    private var isDownload = false
    private var isFinish = false
    private var localStr = ""

    private val SAVE_KEY_SELECTED_TAB = "selectedTab"

    private var mContainerId: Int = 0
    private var mCurrentFragment: Fragment? = null
    private var mCurrentTabId: Int = 0

    private var lastTime: Long = 0

    override fun createViewModel(): MainViewModel = getViewModel(MainViewModel::class.java)

    override fun getLayout(): Int = R.layout.activity_main

    override fun enableImmersionBar(): Boolean = true

    override fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return immersionBar?.statusBarColor(R.color.defaultWhite)
                ?.statusBarDarkFont(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val tabSelected = savedInstanceState.getInt(SAVE_KEY_SELECTED_TAB, 0)
            if (tabSelected > 0) {
                switchFragment(tabSelected)
            }
        } else {
            switchFragment(R.id.nav_order)
        }
        navigation.itemIconTintList = null
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun initView() {
        BtUtil.registerBluetoothReceiver(mBtReceiver, this)
        super.initView()
        mContainerId = R.id.main_container
    }

    override fun initData() {
        Constants.isBondBluetooth = PrintUtil.isBondPrinter(this, BluetoothAdapter.getDefaultAdapter())

        mViewModel?.updateEvent?.observe(this, Observer {
            it?.apply {
                if (checkUpdate()) {
                    updateDialog = GeneralDialog(this@MainActivity)
                            .setTitle("版本更新")
                            .setMessage("发现新版本$version")
                            .setPositiveBtn("立即更新") { dialog ->
                                checkFilePermission(androidUrl)
                            }.setNegativeBtn("取消") { dialog ->
                                dialog.dismiss()
                                if (isDownload) {
                                    mDownloadUtil.downloadCancel()
                                }
                            }
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun checkFilePermission(updateUrl: String) {
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { // will emit 2 Permission objects
                    permission ->
                    when {
                        permission.granted -> {
                            if (!isDownload) {
                                download(updateUrl)
                            }
                        }
                        permission.shouldShowRequestPermissionRationale -> {
                            ToastUtils.showShort("权限不足，请开启存储权限")
                        }
                        else -> {
                            ToastUtils.showShort("权限不足，请开启存储权限")
                        }
                    }
                }

    }

    private fun download(updateUrl: String) {
        if (isFinish && !localStr.isNullOrBlank()) {
            AppUtils.installApp(localStr)
        } else {
            mDownloadUtil = DownloadUtil()
            mDownloadUtil.downloadFile(updateUrl, getString(R.string.app_name), object : DownloadListener {
                override fun onStart() {
                    isDownload = true
                }

                override fun onProgress(currentLength: Int) {
                    updateDialog.setProgress(currentLength)
                }

                override fun onFinish(localPath: String) {
                    AppUtils.installApp(localPath)
                    isDownload = false
                    isFinish = true
                    localStr = localPath
                }

                override fun onFailure(erroInfo: String) {
                    ToastUtils.showShort("下载失败")
                    isDownload = false
                }
            })
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_order -> {
                return@OnNavigationItemSelectedListener switchFragment(R.id.nav_order)
            }
            R.id.nav_mine -> {
                return@OnNavigationItemSelectedListener switchFragment(R.id.nav_mine)
            }
        }
        false
    }

    /**
     * 切换Fragment
     *
     * @param tabId
     * @return 是否切换成功
     */
    private fun switchFragment(tabId: Int): Boolean {

        if (mCurrentTabId == tabId) {
            val nowTime = System.currentTimeMillis()
            if (nowTime - lastTime < 2000) {
                lastTime = nowTime
            }
            return true
        }

        val fm = supportFragmentManager
        var targetFragment: Fragment? = fm.findFragmentByTag(getFragmentTag(tabId))
        var isCreate = false

        if (targetFragment == null) {
            targetFragment = createFragment(tabId)
            isCreate = true
        }

        if (targetFragment !== mCurrentFragment) {

            val transaction = fm.beginTransaction()

            mCurrentFragment?.let {
                transaction.hide(it)
            }

            if (isCreate) {
                transaction.add(mContainerId, targetFragment, getFragmentTag(tabId))
            } else {
                transaction.show(targetFragment)
            }

            transaction.commitAllowingStateLoss()
            mCurrentFragment = targetFragment
            mCurrentTabId = tabId

            return true
        }

        return false
    }

    /**
     * 在这里创建Fragment
     */
    private fun createFragment(tabId: Int): Fragment {
        return when (tabId) {
            R.id.nav_order -> OrderFragment.newInstance()
            R.id.nav_mine -> MineFragment.newInstance()
            else -> MineFragment.newInstance()
        }
    }

    /**
     * 获取Fragment的Tag
     */
    private fun getFragmentTag(tabId: Int): String {
        return "main_frag_$tabId"
    }

    override fun btStartDiscovery(intent: Intent?) {
        LogUtils.d("btStartDiscovery")
    }

    override fun btFinishDiscovery(intent: Intent?) {
        LogUtils.d("btFinishDiscovery")
    }

    override fun btStatusChanged(intent: Intent?) {
        LogUtils.d("btStatusChanged")
        Constants.isBondBluetooth = PrintUtil.isBondPrinter(this, BluetoothAdapter.getDefaultAdapter())
    }

    override fun btFoundDevice(intent: Intent?) {
        LogUtils.d("btFoundDevice")
    }

    override fun btBondStatusChange(intent: Intent?) {

        Constants.isBondBluetooth = PrintUtil.isBondPrinter(this, BluetoothAdapter.getDefaultAdapter())

        LogUtils.d("btBondStatusChange")
    }

    override fun btPairingRequest(intent: Intent?) {
        LogUtils.d("btPairingRequest")
    }

    /**
     * blue tooth broadcast receiver
     */
    protected var mBtReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (null == intent) {
                return
            }
            val action = intent.action
            if (TextUtils.isEmpty(action)) {
                return
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED == action) {
                btStartDiscovery(intent)
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                btFinishDiscovery(intent)
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED == action) {
                btStatusChanged(intent)
            } else if (BluetoothDevice.ACTION_FOUND == action) {
                btFoundDevice(intent)
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED == action) {
                btBondStatusChange(intent)
            } else if ("android.bluetooth.device.action.PAIRING_REQUEST" == action) {
                btPairingRequest(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BtUtil.unregisterBluetoothReceiver(mBtReceiver, this)
    }

    @Subscribe
    fun onEventMainThread(event: PrintMsgEvent) {
        if (event.type === PrinterMsgType.MESSAGE_TOAST) {
            ToastUtils.showShort(event.msg)
        }
    }

}
