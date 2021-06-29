package com.alibaba.android.arouter.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.demo.test.inject.TestObj;
import com.alibaba.android.arouter.demo.test.inject.TestParcelable;
import com.alibaba.android.arouter.demo.test.inject.TestSerializable;
import com.alibaba.android.arouter.demo.test.service.HelloService;
import com.alibaba.android.arouter.demo.test.service.SingleServiceImpl;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            case R.id.openLog:
                ARouter.openLog();
                ARouter.printStackTrace();
                break;
            case R.id.openDebug:
                ARouter.openDebug();
                break;
            case R.id.init:
                // 调试模式不是必须开启，但是为了防止有用户开启了InstantRun，但是
                // 忘了开调试模式，导致无法使用Demo，如果使用了InstantRun，必须在
                // 初始化之前开启调试模式，但是上线前需要关闭，InstantRun仅用于开
                // 发阶段，线上开启调试模式有安全风险，可以使用BuildConfig.DEBUG
                // 来区分环境
                ARouter.openDebug();
                ARouter.init(getApplication());
                break;
            case R.id.destroy:
                ARouter.getInstance().destroy();
                break;
            // ----------------------------------------------------------------------------
            // ---- 基础功能
            // ----------------------------------------------------------------------------
            case R.id.normalNavigation: // 简单的应用内跳转
                ARouter.getInstance()
                        .build("/test/activity2")
                        .navigation();
                break;
            case R.id.normalNavigationWithParams:
                ARouter.getInstance()
                        .build("/test/activity2")
                        .withString("key1", "value1")
                        .navigation();

                // Uri testUriMix = Uri.parse("arouter://m.aliyun.com/test/activity2");
                // ARouter.getInstance().build(testUriMix)
                //         .withString("key1", "value1")
                //         .navigation();
                break;
            case R.id.normalNavigationWithInheritParams:
                ARouter.getInstance()
                        .build("/module_kotlin/inherit_wired")
                        .withString("parentName", "老王他爸")
                        .withInt("parentAge", 56)
                        .withString("name", "老王")
                        .withInt("age", 23)
                        .navigation();
                break;
            case R.id.navigation2TestFragment:
                ARouter.getInstance().build("/test/test_fragment").navigation();
                break;
            case R.id.normalNavigation2: // 带ForResult跳转
                ARouter.getInstance()
                        .build("/test/activity2")
                        .navigation(this, 666);
                break;
            case R.id.getFragment:
                Fragment fragment = (Fragment) ARouter.getInstance().build("/test/fragment").navigation();
                Toast.makeText(this, "找到Fragment:" + fragment.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.oldVersionAnim:
                ARouter.getInstance()
                        .build("/test/activity2")
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation(this);
                break;
            case R.id.newVersionAnim:
                if (Build.VERSION.SDK_INT >= 16) {
                    ARouter.getInstance()
                            .build("/test/activity2")
                            .withOptionsCompat(ActivityOptionsCompat.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0))
                            .navigation();
                } else {
                    Toast.makeText(this, "API < 16,不支持新版本动画", Toast.LENGTH_SHORT).show();
                }
                break;
            // ----------------------------------------------------------------------------
            // ---- 进阶功能
            // ----------------------------------------------------------------------------
            case R.id.navByUrl:
                ARouter.getInstance()
                        .build("/test/webview")
                        .withString("url", "file:///android_asset/scheme-test.html")
                        .navigation();
                break;
            case R.id.interceptor:
                ARouter.getInstance()
                        .build("/test/activity4")
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.d("ARouter", "被拦截了");
                            }
                        });
                break;
            case R.id.autoInject:
                TestSerializable testSerializable = new TestSerializable("Titanic", 555);
                TestParcelable testParcelable = new TestParcelable("jack", 666);
                TestObj testObj = new TestObj("Rose", 777);
                List<TestObj> objList = new ArrayList<>();
                objList.add(testObj);

                Map<String, List<TestObj>> map = new HashMap<>();
                map.put("testMap", objList);

                ARouter.getInstance().build("/test/activity1")
                        .withString("name", "老王")
                        .withInt("age", 18)
                        .withBoolean("boy", true)
                        .withLong("high", 180)
                        .withString("url", "https://a.b.c")
                        .withSerializable("ser", testSerializable)
                        .withParcelable("pac", testParcelable)
                        .withObject("obj", testObj)
                        .withObject("objList", objList)
                        .withObject("map", map)
                        .navigation();
                break;
            // ----------------------------------------------------------------------------
            // ---- 服务
            // ----------------------------------------------------------------------------
            case R.id.navByName:
                ((HelloService) ARouter.getInstance().build("/yourservicegroupname/hello").navigation()).sayHello("mike");
                break;
            case R.id.navByType:
                ARouter.getInstance().navigation(HelloService.class).sayHello("mike");
                break;
            case R.id.callSingle:
                ARouter.getInstance().navigation(SingleServiceImpl.class).sayHello("Mike");
                break;
            // ----------------------------------------------------------------------------
            // ---- 多模块
            // ----------------------------------------------------------------------------
            case R.id.navToMoudle1:
                ARouter.getInstance().build("/module_java/1").navigation();
                break;
            case R.id.navToMoudle2:
                // 这个页面主动指定了Group名
                ARouter.getInstance().build("/module_java/2", "m2").navigation();
                break;
            case R.id.navToMoudle3:
                ARouter.getInstance().build("/module_kotlin/1").navigation();
                break;
            // ----------------------------------------------------------------------------
            // ---- 跳转失败
            // ----------------------------------------------------------------------------
            case R.id.failNav: // 跳转失败，单独降级
                ARouter.getInstance().build("/xxx/xxx").navigation(this, new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d("ARouter", "找到了");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d("ARouter", "找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d("ARouter", "跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d("ARouter", "被拦截了");
                    }
                });
                break;
            case R.id.failNav2: // 跳转失败，全局降级
                ARouter.getInstance().build("/xxx/xxx").navigation();
                break;
            case R.id.failNav3: // 服务调用失败
                ARouter.getInstance().navigation(MainActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666) {
            Toast.makeText(this, String.format("requestCode:%s, resultCode:%s", requestCode, resultCode), Toast.LENGTH_SHORT).show();
        }
    }
}
