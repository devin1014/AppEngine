package com.alibaba.android.arouter.demo.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.alibaba.android.arouter.demo.R;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/app/webview")
public class WebViewActivity extends Activity {

    @Autowired
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ARouter.getInstance().inject(this);
        ((WebView) findViewById(R.id.webview)).loadUrl(url);
    }
}
