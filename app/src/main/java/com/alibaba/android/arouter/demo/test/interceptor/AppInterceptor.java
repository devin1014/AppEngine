package com.alibaba.android.arouter.demo.test.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 *
 */
@Interceptor(priority = 7)
public class AppInterceptor implements IInterceptor {

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        if ("/module_java/target2".equals(postcard.getPath())) {
            callback.onInterrupt(new IllegalArgumentException("can not route to path: " + postcard.getPath()));
        } else {
            callback.onContinue(postcard);
        }
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        ARouter.logger.info(getClass().getSimpleName(), getClass().getSimpleName() + " has init.");
    }
}
