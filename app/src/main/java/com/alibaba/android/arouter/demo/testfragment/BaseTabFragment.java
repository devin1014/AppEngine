package com.alibaba.android.arouter.demo.testfragment;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Autowired;

public abstract class BaseTabFragment extends Fragment
{
    @Autowired
    String parentName;

    @Autowired
    int parentAge;
}
