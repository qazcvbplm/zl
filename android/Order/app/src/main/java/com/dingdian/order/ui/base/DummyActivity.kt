package com.dingdian.order.ui.base

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

class DummyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Handler().postDelayed({
            finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }, 500)
    }
}