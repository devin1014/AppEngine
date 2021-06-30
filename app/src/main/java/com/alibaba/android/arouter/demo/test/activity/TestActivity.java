package com.alibaba.android.arouter.demo.test.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.demo.R;
import com.alibaba.android.arouter.demo.test.bean.ParamObject;
import com.alibaba.android.arouter.demo.test.service.ToastService;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;
import java.util.Map;

/**
 * https://m.aliyun.com/test/activity1?name=老王&age=23&boy=true&high=180
 */
@Route(path = "/app/activity1", name = "测试用 Activity")
public class TestActivity extends AppCompatActivity {

    @Autowired(desc = "姓名")
    String name = "jack";

    @Autowired
    int age = 10;

    @Autowired
    int height = 175;

    @Autowired(name = "boy", required = true)
    boolean girl;

    @Autowired
    char ch = 'A';

    @Autowired
    float fl = 12.00f;

    @Autowired
    double dou = 12.01d;

    @Autowired
    ParamObject ser;

    @Autowired
    ParamObject pac;

    @Autowired
    ParamObject obj;

    @Autowired
    List<ParamObject> objList;

    @Autowired
    Map<String, List<ParamObject>> map;

    @Autowired
    String url;

    @Autowired
    ToastService toastService;

    private long high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        ARouter.getInstance().inject(this);

        // No more getter ...
        // name = getIntent().getStringExtra("name");
        // age = getIntent().getIntExtra("age", 0);
        // girl = getIntent().getBooleanExtra("girl", false);
        // high = getIntent().getLongExtra("high", 0);
        // url = getIntent().getStringExtra("url");

        String params = String.format(
                "name=%s,\n age=%s, \n height=%s,\n girl=%s,\n high=%s,\n url=%s,\n ser=%s,\n pac=%s,\n obj=%s \n ch=%s \n fl = %s, \n dou = %s, \n objList=%s, \n map=%s",
                name,
                age,
                height,
                girl,
                high,
                url,
                ser,
                pac,
                obj,
                ch,
                fl,
                dou,
                objList,
                map
        );
        toastService.toast("Hello moto.");

        ((TextView) findViewById(R.id.content)).setText(params);
    }
}
