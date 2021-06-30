package com.alibaba.android.arouter.demo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.demo.test.bean.ParamObject;
import com.alibaba.android.arouter.demo.test.bean.ParamParcelable;
import com.alibaba.android.arouter.demo.test.bean.ParamSerializable;
import com.alibaba.android.arouter.demo.test.service.SingleServiceImpl;
import com.alibaba.android.arouter.demo.test.service.ToastService;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("NonConstantResourceId")
    @SuppressWarnings("SwitchStatementWithoutDefaultBranch")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // ----------------------------------------------------------------------------
            // ---- 基础功能
            // ----------------------------------------------------------------------------
            case R.id.normalNavigation: // 简单的应用内跳转
                ARouter.getInstance()
                        .build("/module_java/target1")
                        .navigation();
                break;
            case R.id.normalNavigationWithParams:
                ARouter.getInstance()
                        .build("/module_java/target1")
                        .withString("name", "AA")
                        .withString("description", "bb")
                        .withString("key1", "value1")
                        .withString("key2", "value2")
                        .withObject("obj", new ParamObject("ccc", "ddd"))
                        .navigation(this, 666, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                // do nothing
                            }
                        });
                break;
            case R.id.navigation2TestFragment:
                ARouter.getInstance().build("/app/fragment_tabs").navigation();
                break;
            case R.id.oldVersionAnim:
                ARouter.getInstance()
                        .build("/module_java/target1")
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation();
                break;
            case R.id.newVersionAnim:
                ARouter.getInstance()
                        .build("/module_java/target1")
                        .withOptionsCompat(ActivityOptionsCompat.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0))
                        .navigation();
                break;
            // ----------------------------------------------------------------------------
            // ---- 进阶功能
            // ----------------------------------------------------------------------------
            case R.id.navByUrl:
                ARouter.getInstance()
                        .build("/app/webview")
                        .withString("url", "file:///android_asset/scheme-test.html")
                        .navigation();
                break;
            case R.id.autoInject:
                ArrayList<ParamObject> list = new ArrayList<>();
                list.add(new ParamObject("DD", "dd"));

                HashMap<String, ParamObject> map = new HashMap<>();
                map.put("key", new ParamObject("EE", "ee"));

                ARouter.getInstance().build("/app/activity_url")
                        .withString("name", "老王")
                        .withInt("age", 18)
                        .withBoolean("boy", true)
                        .withLong("high", 180)
                        .withChar("ch", 'c')
                        .withFloat("fl", 1.0f)
                        .withDouble("dou", 1.0d)
                        .withString("url", "https://test.com")
                        .withSerializable("ser", new ParamSerializable("AA", "aa"))
                        .withParcelable("pac", new ParamParcelable("BB", "bb"))
                        .withObject("obj", new ParamObject("CC", "cc"))
                        .withObject("list", list)
                        .withObject("map", map)
                        .navigation();
                break;
            case R.id.interceptor:
                ARouter.getInstance()
                        .build("/module_java/target2", "java")
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                // do nothing
                            }

                            @Override
                            public void onInterrupt(final Postcard postcard) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setTitle("被拦截了")
                                                .setMessage("URI：" + (postcard.getUri() != null ? postcard.getUri() : postcard.getPath()))
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .show();
                                    }
                                });
                            }
                        });
                break;
            // ----------------------------------------------------------------------------
            // ---- 服务
            // ----------------------------------------------------------------------------
            case R.id.navByName:
                ((ToastService) ARouter.getInstance().build("/app/service/toast").navigation()).toast("hello1");
                ARouter.getInstance().navigation(ToastService.class).toast("hello2");
                ARouter.getInstance().navigation(SingleServiceImpl.class).sayHello("hello3");
                break;
            // ----------------------------------------------------------------------------
            // ---- 跳转失败
            // ----------------------------------------------------------------------------
            case R.id.failNav: // 跳转失败，单独降级
                ARouter.getInstance().build("/xxx/xxx").navigation(this, new NavCallback() {
                    @Override
                    public void onLost(Postcard postcard) {
                        ARouter.logger.warning("ARouter", "跳转失败了，找不到目标：" + postcard.toString());
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        // do nothing
                    }
                });
                break;
            case R.id.failNav2: // 跳转失败，全局降级
                ARouter.getInstance().build("/xxx/xxx").navigation();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666) {
            Toast.makeText(this, "onActivityResult: " + requestCode + "," + resultCode, Toast.LENGTH_SHORT).show();
        }
    }
}
