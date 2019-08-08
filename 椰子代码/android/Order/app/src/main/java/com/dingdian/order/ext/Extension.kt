package com.dingdian.order

import android.content.Context
import android.widget.Toast
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5(): String {
    try {
        val instance: MessageDigest = MessageDigest.getInstance("MD5")//获取md5加密对象
        val digest: ByteArray = instance.digest(this.toByteArray())//对字符串加密，返回字节数组
        var sb: StringBuffer = StringBuffer()
        for (b in digest) {
            var i: Int = b.toInt() and 0xff//获取低八位有效值
            var hexString = Integer.toHexString(i)//将整数转化为16进制
            if (hexString.length < 2) {
                hexString = "0" + hexString//如果是一位的话，补0
            }
            sb.append(hexString)
        }
        return sb.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

fun Context.showShort(message: String = "") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showShort(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

fun Context.showLong(message: String = "") {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showLong(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}