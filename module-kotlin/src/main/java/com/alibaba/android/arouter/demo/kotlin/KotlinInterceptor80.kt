package com.alibaba.android.arouter.demo.kotlin

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter

/**
 *
 */
@Interceptor(priority = 80)
class KotlinInterceptor80 : IInterceptor {
    /**
     * The operation of this tollgate.
     *
     * @param postcard meta
     * @param callback cb
     */
    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        callback.onContinue(postcard)
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    override fun init(context: Context) {
        ARouter.logger.info(javaClass.simpleName, javaClass.simpleName + " has init.")
    }
}