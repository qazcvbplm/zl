package com.dingdian.order.ui.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.ToastUtils;
import com.dingdian.order.ui.main.mine.MineFragment;
import com.dingdian.order.ui.main.order.unreceive.UnReceiveFragment;
import com.dingdian.order.ui.widget.print.PrintUtil;

/**
 * Created by liuguirong on 8/1/17.
 */

public class BluetoothController {

    public static void init(UnReceiveFragment activity) {
        if (null == activity.getMAdapter()) {
            activity.setMAdapter(BluetoothAdapter.getDefaultAdapter());
        }
        if (null == activity.getMAdapter()) {
//            activity.tv_bluename.setText("该设备没有蓝牙模块");
//            activity.mBtEnable = false;
            ToastUtils.showShort("该设备没有蓝牙模块");
            return;
        }
        Log.d("activity.mAdapter.getState()","activity.mAdapter.getState()"+activity.getMAdapter().getState());
        if (!activity.getMAdapter().isEnabled()) {
            //没有在开启中也没有打开
//            if ( activity.mAdapter.getState()!=BluetoothAdapter.STATE_TURNING_ON  && activity.mAdapter.getState()!=BluetoothAdapter.STATE_ON ){
            if ( activity.getMAdapter().getState()==BluetoothAdapter.STATE_OFF ){//蓝牙被关闭时强制打开
                 activity.getMAdapter().enable();

            }else {
//                activity.tv_bluename.setText("蓝牙未打开");
                ToastUtils.showShort("蓝牙未打开");
                return;
            }
        }
        String address = PrintUtil.getDefaultBluethoothDeviceAddress(activity.getContext().getApplicationContext());
        if (TextUtils.isEmpty(address)) {
//            activity.tv_bluename.setText("尚未绑定蓝牙设备");
            ToastUtils.showShort("尚未绑定蓝牙设备");
            return;
        }
        String name = PrintUtil.getDefaultBluetoothDeviceName(activity.getContext().getApplicationContext());
//        activity.tv_bluename.setText("已绑定蓝牙：" + name);
//        ToastUtils.showShort("已连接");
//        activity.tv_blueadress.setText(address);

    }
    public static boolean turnOnBluetooth()
    {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (bluetoothAdapter != null)
        {
            return bluetoothAdapter.enable();
        }
        return false;
    }
}
