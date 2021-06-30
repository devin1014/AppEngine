package com.alibaba.android.arouter.demo.test.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.demo.R;
import com.alibaba.android.arouter.demo.test.bean.ParamObject;
import com.alibaba.android.arouter.demo.test.service.ToastService;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * https://m.aliyun.com/test/activity1?name=老王&age=23&boy=true&high=180
 */
@Route(path = "/app/activity_url", name = "测试用 Activity")
public class AutowiredActivity extends AppCompatActivity {

    @Autowired(desc = "姓名")
    String name = "";

    @Autowired
    int age = 0;

    @Autowired
    int height = 0;

    @Autowired(name = "boy", required = true)
    boolean girl;

    @Autowired
    char ch = 'A';

    @Autowired
    float fl = 0.00f;

    @Autowired
    double dou = 0.00d;

    @Autowired
    String url;

    @Autowired
    Serializable ser;

    @Autowired
    Parcelable pac;

    @Autowired
    ParamObject obj;

    @Autowired
    List<ParamObject> list;

    @Autowired
    Map<String, ParamObject> map;

    @Autowired
    ToastService toastService;

    @SuppressWarnings("FieldCanBeLocal")
    private final long high = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autowired);

        ARouter.getInstance().inject(this);

        // No more getter ...
        // name = getIntent().getStringExtra("name");
        // age = getIntent().getIntExtra("age", 0);
        // girl = getIntent().getBooleanExtra("girl", false);
        // high = getIntent().getLongExtra("high", 0);
        // url = getIntent().getStringExtra("url");

        ((TextView) findViewById(R.id.content)).setText(
                String.format(
                        "name: %s,\nage: %s, \nheight: %s,\ngirl: %s,\nhigh: %s,\nch: %s,\nfl: %s,\ndou: %s,\nurl: %s,\n\nser: %s,\npac: %s,\nobj: %s,\nlist: %s,\nmap: %s",
                        name,
                        age,
                        height,
                        !girl,
                        high,
                        ch,
                        fl,
                        dou,
                        url,
                        ser,
                        pac,
                        obj,
                        list,
                        map
                ));

        toastService.toast("Hello moto.");
    }
}
