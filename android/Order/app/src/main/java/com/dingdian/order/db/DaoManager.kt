package com.dingdian.order.db

import android.content.Context
import com.dingdian.order.comm.Constants.DB_NAME
import com.dingdian.order.comm.Constants.DB_PWD
import com.dingdian.order.db.greendao.DaoMaster
import com.dingdian.order.db.greendao.DaoSession
import com.dingdian.order.db.greendao.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaoManager @Inject constructor(app: Context) {

    var daoMaster: DaoMaster

    var daoSession: DaoSession

    init {
        val helper = DaoMaster.DevOpenHelper(app.applicationContext, DB_NAME, null)
        //获取可写数据库
        val db = helper.getEncryptedWritableDb(DB_PWD)
        //获取数据库对象
        daoMaster = DaoMaster(db)
        //获取Dao对象管理者
        daoSession = daoMaster?.newSession()
    }

    fun userDao(): UserDao = daoSession.userDao
}