package com.dingdian.order.ui.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.DefaultLifecycleObserver

open class BaseViewModule<T : BaseRepository> constructor(app: Application, repository: T) : AndroidViewModel(app), DefaultLifecycleObserver