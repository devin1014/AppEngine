package com.alibaba.android.arouter.demo.testfragment;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Autowired;

public abstract class BaseTabFragment extends Fragment
{
    @Autowired
    String parentName;

    @Autowired
    int parentAge;
}
