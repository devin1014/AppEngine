package com.alibaba.android.arouter.demo.module1;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 *
 */
@Interceptor(priority = 90)
public class JavaInterceptor90 implements IInterceptor {
    /**
     * The operation of this tollgate.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        callback.onContinue(postcard);
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
