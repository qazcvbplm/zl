package com.dingdian.order.ui.main.mine

import android.Manifest
import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.graphics.Color
import cn.addapp.pickers.picker.DatePicker
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.dingdian.order.R
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.Constants
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.databinding.MineFragmentBinding
import com.dingdian.order.ui.base.MVVMFragment
import com.dingdian.order.ui.bluetooth.SearchBluetoothActivity
import com.dingdian.order.ui.shop.TxActivity
import com.dingdian.order.ui.widget.print.PrintUtil
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.mine_fragment.*
import java.util.*


class MineFragment : MVVMFragment<MineViewModel, MineFragmentBinding>() {

    companion object {
        fun newInstance() = MineFragment()
    }

    val rxPermissions by lazy { RxPermissions(activity!!) }

    override fun createViewModel(): MineViewModel = getViewModel(MineViewModel::class.java)

    override fun getLayout(): Int = R.layout.mine_fragment

    override fun initView() {
        super.initView()
        mBinding?.viewModel = mViewModel

        LocalUser.autoReceive().observe(this, Observer {
            it?.let {
                if (it == autoSwitch.isChecked) {
                } else {
                    autoSwitch.isChecked = it
                }
            }
        })

        autoSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!PrintUtil.isBondPrinter(context, BluetoothAdapter.getDefaultAdapter())) {
                autoSwitch.isChecked = false
                ToastUtils.showShort("先连接蓝牙")
                return@setOnCheckedChangeListener
            }
            LocalUser.updateAutoReceive(isChecked)
        }

        LocalUser.smallFont().observe(this, Observer {
            it?.let {
                if (it == smallSwitch.isChecked) {
                } else {
                    smallSwitch.isChecked = it
                }
            }
        })

        smallSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            LocalUser.updateSmallFont(isChecked)
        }

        startTime.setOnClickListener {
            //            val datePickerDialog = DatePickerDialog(context)
//            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
//                mViewModel?.startDate = year.toString() + "-" + (month + 1) + "-" + dayOfMonth + mViewModel?.startTime
//                startTime.text = year.toString() + "-" + (month + 1) + "-" + dayOfMonth + mViewModel?.startTime
//                startTime.setTextColor(Color.parseColor("#333333"))
//            }
//            datePickerDialog.show()

            val picker = DatePicker(activity)
            picker.setCanLoop(true)
            picker.setWheelModeEnable(true)
            picker.setTopPadding(15)
            picker.setRangeStart(2016, 8, 29)
            picker.setRangeEnd(2111, 1, 11)
            picker.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            picker.setWeightEnable(true)
            picker.setLineColor(Color.BLACK)
            picker.setOnDatePickListener(DatePicker.OnYearMonthDayPickListener { year, month, dayOfMonth ->
                mViewModel?.startDate = year.toString() + "-" + (month) + "-" + dayOfMonth + mViewModel?.startTime
                startTime.text = year.toString() + "-" + (month) + "-" + dayOfMonth + mViewModel?.startTime
                startTime.setTextColor(Color.parseColor("#333333"))
            })
            picker.show()
        }

        endTime.setOnClickListener {
            val picker = DatePicker(activity)
            picker.setCanLoop(true)
            picker.setWheelModeEnable(true)
            picker.setTopPadding(15)
            picker.setRangeStart(2016, 8, 29)
            picker.setRangeEnd(2111, 1, 11)
            picker.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            picker.setWeightEnable(true)
            picker.setLineColor(Color.BLACK)
            picker.setOnDatePickListener(DatePicker.OnYearMonthDayPickListener { year, month, dayOfMonth ->
                mViewModel?.endDate = year.toString() + "-" + (month) + "-" + dayOfMonth + mViewModel?.endTime
                endTime.text = year.toString() + "-" + (month) + "-" + dayOfMonth + mViewModel?.endTime
                endTime.setTextColor(Color.parseColor("#333333"))
            })
            picker.show()
        }

        //拍照
        RxView.clicks(bluetoothBtn)
                .compose<Permission>(
                        rxPermissions.ensureEachCombined(
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                )
                .subscribe({ permission ->
                    if (permission.granted) {
                        //全部允许
                        startActivity(Intent(context, SearchBluetoothActivity::class.java))
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        //起码有一项拒绝，没有选中不再询问
                        ToastUtils.showShort("权限不足，请到设置界面开启权限")
                    } else {
                        //至少一项拒绝，并且不再询问
                        ToastUtils.showShort("权限不足，请到设置界面开启权限")
                    }
                }, { throwable ->
                    LogUtils.d(throwable)
                })



        RxView.clicks(tx).subscribe({ permission ->
            //全部允许
            ARouter.getInstance().build(ArouterPath.tx).navigation()
        })

    }

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
        bluetoothStatus()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            bluetoothStatus()
        }
    }

    fun bluetoothStatus() {
        if (Constants.isBondBluetooth) {
            bluetoothBtn.text = "蓝牙已连接"
        } else {
            bluetoothBtn.text = "蓝牙未连接"
        }
    }

}
