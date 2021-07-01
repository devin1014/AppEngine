package com.alibaba.android.arouter.demo.test.service;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 *
 */
@Route(path = "/app/service/toast")
public class ToastServiceImpl implements ToastService {
    private Context mContext;

    @Override
    public void init(Context context) {
        mContext = context.getApplicationContext();
        ARouter.logger.info(getClass().getSimpleName(), getClass().getSimpleName() + " has init.");
    }

    @Override
    public void toast(String message) {
        ARouter.logger.info("ToastServiceImpl", "toast: " + message);
        Toast.makeText(mContext, "toast: " + message, Toast.LENGTH_SHORT).show();
    }
}
