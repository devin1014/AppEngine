package com.alibaba.android.arouter.demo.test.service;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/service/degrade")
public class AppDegradeService implements DegradeService {
    private Context mContext;

    @Override
    public void init(Context context) {
        mContext = context;
        ARouter.logger.info(getClass().getSimpleName(), getClass().getSimpleName() + " has init.");
    }

    @Override
    public void onLost(Context context, Postcard postcard) {
        ARouter.logger.warning(getClass().getSimpleName(), "onLost: " + postcard.toString());
        Toast.makeText(mContext, "onLost: " + postcard.getPath(), Toast.LENGTH_SHORT).show();
    }
}
