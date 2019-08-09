package com.dingdian.order.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import android.text.TextUtils.isEmpty
import com.blankj.utilcode.util.LogUtils
import com.dingdian.order.md5
import com.dingdian.order.ui.App
import com.dingdian.order.utils.SharePreUtil.device_group
import com.dingdian.order.utils.SharePreUtil.uuid_key
import java.util.*

object DeviceUtil {

    private fun getMacAddress(context: Context): String? {
        val wifi = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = wifi.connectionInfo
        if (info == null || info.macAddress == null) {
            return null
        }
        return if ("02:00:00:00:00:00" == info.macAddress.trim { it <= ' ' }) {
            null
        } else {
            info.macAddress.trim { it <= ' ' }
        }
    }

    @Throws(SecurityException::class)
    private fun getImei(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.deviceId
    }

    @Throws(SecurityException::class)
    private fun getSimSerialNumber(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.simSerialNumber
    }


    /**
     * uuid存在则直接使用，否则获取mac地址、imei、sn、任意一个则返回，否则生成uuid并保存
     * @param context
     * @return
     */
    fun getDeviceId(context: Context): String {
        var deviceId: String?
        try {
            deviceId = getUUID()

            if (!isEmpty(deviceId)) {
                return deviceId
            }
            deviceId = getMacAddress(context)
            if (!isEmpty(deviceId)) {
                return saveDeviceId(deviceId!!)
            }
            deviceId = getImei(context)
            if (!isEmpty(deviceId)) {
                return saveDeviceId(deviceId)
            }
            deviceId = getSimSerialNumber(context)
            if (!isEmpty(deviceId)) {
                return saveDeviceId(deviceId)
            }
            //如果上面都没有， 则生成一个id：随机码
            deviceId = getOrGenerateUUID()
            if (!isEmpty(deviceId)) {
                return deviceId.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            deviceId = getOrGenerateUUID()
        }

        return deviceId!!.toString()
    }

    /**
     * 得到全局唯一UUID
     */
    private fun getOrGenerateUUID(): String {
        var uuid = ""
        val mShare = SharePreUtil.getInstance(device_group)
        uuid = mShare.getString(uuid_key, "")
        if (isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString()
            uuid = uuid.md5()
            mShare!!.put(uuid_key, uuid)
        }
        return uuid
    }

    private fun getUUID(): String {
        var uuid = ""
        val mShare = SharePreUtil.getInstance(device_group)
        if (mShare != null) {
            uuid = mShare.getString(uuid_key, "")
        }
        return uuid
    }

    private fun saveDeviceId(deviceId: String): String {
        val result = deviceId.md5()
        val mShare = SharePreUtil.getInstance(device_group)
        mShare.put(uuid_key, result)
        return result
    }

    fun getPhoneModel(): String {
        LogUtils.d(
                "BRAND = ${android.os.Build.BRAND}\n" +
                        "PRODUCT=${android.os.Build.PRODUCT}\n" +
                        "BOARD=${android.os.Build.BOARD}\n" +
                        "MODEL =${android.os.Build.MODEL}\n" +
                        "HARDWARE=${android.os.Build.HARDWARE}\n"
        )

        //手机厂商 + 手机型号 如 Xiaomi MI 5s Plus
        return android.os.Build.BRAND + " " + android.os.Build.MODEL
    }

    fun getCurrentVersion(): String {
        // 获取packagemanager的实例
        val packageManager = App.appComponent!!.getApplication().packageManager
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        val packInfo = packageManager.getPackageInfo(App.appComponent!!.getApplication().packageName, 0)
        return packInfo.versionName
    }

    /**
     * version1 < version2 return -1
     * version1 == version2 return 0
     * version1 > version2 return 1
     */
    fun compareVersion(version1: String?, version2: String?): Int {

        if (version1 == null || version1.isEmpty())
            return -1

        if (version2 == null || version2.isEmpty())
            return 1

        if (version1 == version2) {
            return 0
        }

        val version1Array = version1.split(".")
        val version2Array = version2.split(".")
        var index = 0
        // 获取最小长度值
        var minLen = Math.min(version1Array.size, version2Array.size)
        var diff = 0
        // 循环判断每位的大小
        diff = version1Array[index].toInt() - version2Array[index].toInt()
        while (index < minLen
                && diff == 0
        ) {
            diff = version1Array[index].toInt() - version2Array[index].toInt()
            index++
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (i in index..version1Array.lastIndex) {
                if (version1Array[i].toInt() > 0) {
                    return 1
                }
            }

            for (i in index..version2Array.lastIndex) {
                if (version2Array[i].toInt() > 0) {
                    return -1
                }
            }

            return 0
        } else {
            return if (diff > 0) 1 else -1
        }
    }

    fun callPhone(context: Context, phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}