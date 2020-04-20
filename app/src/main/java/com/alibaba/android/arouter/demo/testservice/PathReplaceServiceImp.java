package com.alibaba.android.arouter.demo.testservice;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

@Route(path = "/yourservicegroupname/path_replace")
public class PathReplaceServiceImp implements PathReplaceService
{
    @Override
    public String forString(String path)
    {
        return path;
    }

    @Override
    public Uri forUri(Uri uri)
    {
        return uri;
    }

    @Override
    public void init(Context context)
    {
    }
}
