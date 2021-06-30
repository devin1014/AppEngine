package com.alibaba.android.arouter.demo.module1;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;

abstract class JavaBaseActivity extends AppCompatActivity {

    @Autowired
    public String name;

    @Autowired
    public String description;
}
