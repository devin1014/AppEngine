//package com.alibaba.android.arouter.app.service
//
//import android.content.Context
//import com.alibaba.android.arouter.app.Constants
//import com.alibaba.android.arouter.app.core.getAppService
//import com.alibaba.android.arouter.facade.Postcard
//import com.alibaba.android.arouter.facade.annotation.Interceptor
//import com.alibaba.android.arouter.facade.callback.InterceptorCallback
//import com.alibaba.android.arouter.facade.template.IInterceptor
//import com.alibaba.android.arouter.launcher.ARouter
//
///**
// *
// */
//@Interceptor(priority = 81)
//class AuthInterceptor : IInterceptor {
//
//    override fun init(context: Context) {
//        ARouter.logger.info(javaClass.simpleName, javaClass.simpleName + " has init.")
//    }
//
//    override fun process(postcard: Postcard, callback: InterceptorCallback) {
//        if (postcard.path == Constants.ROUTER_FRAGMENT_AUTH_SIGNIN && getAppService(AuthService::class).authenticated) {
//            postcard.path = Constants.ROUTER_FRAGMENT_AUTH_INFO
//        }
//        callback.onContinue(postcard)
//    }
//}