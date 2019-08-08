package com.dingdian.order.comm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.blankj.utilcode.util.StringUtils
import com.dingdian.order.db.DaoManager
import com.dingdian.order.db.entity.User
import com.dingdian.order.ui.App
import com.dingdian.order.utils.SharePreUtil

object LocalUser {

    private var daoManager: DaoManager

    private val user = MutableLiveData<User>()

    private val autoReceive = MutableLiveData<Boolean>()

    private val smallFont = MutableLiveData<Boolean>()

    init {
        daoManager = DaoManager(App.appComponent!!.getApplication())
        val userList = daoManager.userDao().queryBuilder().list()
        if (userList.size > 0)
            user.postValue(userList[0])

        val local = SharePreUtil.getInstance(SharePreUtil.commmon_group).getBoolean(SharePreUtil.auto_receive_key, false)
        autoReceive.value = local

        val font = SharePreUtil.getInstance(SharePreUtil.commmon_group).getBoolean(SharePreUtil.small_font_key, false)
        smallFont.value = font
    }

    fun initData() {
        LocalUser.getUser()
    }

    fun isLogin(): Boolean = !(user.value == null || StringUtils.isEmpty(user.value!!.token))

    fun login(user: User) {
        val userDao = daoManager.userDao()
        userDao.deleteAll()
        userDao.insert(user)
        this.user.value = user
    }


    fun logout() {
        val userDao = daoManager.userDao()
        userDao.deleteAll()
        this.user.value = null

    }

    fun updateUser(user: User) {
        val userDao = daoManager.userDao()
        userDao.update(user)
        this.user.value = user
    }

    fun getUser(): LiveData<User> {
        if (user.value == null) {
            val userList = daoManager.userDao().queryBuilder().list()
            if (userList.size > 0)
                user.value = userList[0]
        }
        return user
    }

    fun autoReceive(): MutableLiveData<Boolean> = autoReceive

    fun isAutoReceive(): Boolean = autoReceive.value!!

    fun updateAutoReceive(auto: Boolean) {
        autoReceive.value = auto
        SharePreUtil.getInstance(SharePreUtil.commmon_group).put(SharePreUtil.auto_receive_key, auto)
    }

    fun smallFont(): MutableLiveData<Boolean> = smallFont

    fun isSmallFont(): Boolean = smallFont.value!!

    fun updateSmallFont(small: Boolean) {
        smallFont.value = small
        SharePreUtil.getInstance(SharePreUtil.commmon_group).put(SharePreUtil.small_font_key, small)
    }
}