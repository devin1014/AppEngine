package com.alibaba.android.arouter.demo.test.service;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/app/service/path_replace")
public class PathReplaceServiceImp implements PathReplaceService {
    @Override
    public void init(Context context) {
        ARouter.logger.info(getClass().getSimpleName(), getClass().getSimpleName() + " has init.");
    }

    @Override
    public String forString(String path) {
        return path;
    }

    @Override
    public Uri forUri(Uri uri) {
        return uri;
    }
}
