package com.alibaba.android.arouter.app

import android.os.Bundle
import android.os.Handler
import android.os.Handler.Callback
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.android.appengine.router.buildActivity
import com.android.appengine.router.getAppService
import com.alibaba.android.arouter.app.service.AuthService
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_ACTIVITY_SPLASH)
class SplashActivity : AppCompatActivity(), Callback {

    private val countDownHandler = CountDownHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.printIntentInfo(this, "onCreate")
        setContentView(R.layout.activity_splash)
        countDownHandler.countDown(3000L)
    }

    override fun handleMessage(msg: Message): Boolean {
        getAppService(AuthService::class).initialized = true
        finish()
        buildActivity(Constants.ROUTER_ACTIVITY_MAIN, true)
        return true
    }

    private class CountDownHandler(callback: Callback) : Handler(Looper.getMainLooper(), callback) {
        fun countDown(duration: Long) {
            sendEmptyMessageDelayed(666, duration)
        }
    }
}