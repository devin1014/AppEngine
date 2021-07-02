package com.alibaba.android.arouter.app.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider

interface AuthService : IProvider {
    var initialized: Boolean
}

@Route(path = "/app/service/auth")
class AuthServiceImpl : AuthService {

    private var _initialized = false
    override var initialized: Boolean
        get() = _initialized
        set(value) {
            _initialized = value
        }

    private var applicationContext: Context? = null

    override fun init(context: Context) {
        applicationContext = context.applicationContext
    }
}