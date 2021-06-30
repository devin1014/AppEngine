package com.alibaba.android.arouter.demo.test.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.demo.R;
import com.alibaba.android.arouter.demo.test.bean.ParamObject;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/app/fragment_tabs")
public class FragmentTabsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tabs);

        RadioGroup radioGroup = findViewById(R.id.tab_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_1:
                        showFragment((Fragment) build("/app/fragment/tab1").navigation());
                        break;
                    case R.id.tab_2:
                        showFragment((Fragment) build("/app/fragment/tab2").navigation());
                        break;
                    case R.id.tab_3:
                        showFragment((Fragment) build("/app/fragment/tab3").navigation());
                        break;
                    default:
                        showFragment((Fragment) build("/app/fragment/tabX").navigation());
                        break;
                }
            }
        });
    }

    private Postcard build(String uri) {
        return ARouter.getInstance()
                .build(uri)
                .withString("name", "AA")
                .withString("description", "bb")
                .withString("key1", "value1")
                .withString("key2", uri)
                .withParcelable("obj", new ParamObject("ccc", "ddd"));
    }

    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
