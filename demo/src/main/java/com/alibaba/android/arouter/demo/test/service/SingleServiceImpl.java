package com.alibaba.android.arouter.demo.test.service;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 测试单类注入
 */
@Route(path = "/app/service/single")
public class SingleServiceImpl implements IProvider {
    private Context mContext;

    @Override
    public void init(Context context) {
        mContext = context;
        ARouter.logger.info(getClass().getSimpleName(), getClass().getSimpleName() + " has init.");
    }

    public void sayHello(String name) {
        ARouter.logger.info("SingleServiceImpl", "sayHello: " + name);
        Toast.makeText(mContext, "sayHello: " + name, Toast.LENGTH_SHORT).show();
    }
}
