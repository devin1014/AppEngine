package com.alibaba.android.arouter.demo.module1;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;

@Route(path = "/module_java/target2", group = "java")
public class JavaGroupActivity extends JavaBaseActivity {
    @Autowired
    public String key1;

    @Autowired
    public String key2;

    @Autowired
    public Serializable obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        ARouter.getInstance().inject(this);
        key2 = getIntent().getStringExtra("key2");

        String builder = "name=" + name + "\n" +
                "description=" + description + "\n" +
                "key1=" + key1 + "\n" +
                "key2=" + key2 + "\n" +
                "obj=" + obj + "\n";
        ((TextView) findViewById(R.id.content)).setText(builder);
    }
}
