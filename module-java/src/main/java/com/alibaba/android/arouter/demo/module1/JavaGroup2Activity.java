package com.alibaba.android.arouter.demo.module1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/module_java/2", group = "m2")
public class JavaGroup2Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_group2);
    }
}
