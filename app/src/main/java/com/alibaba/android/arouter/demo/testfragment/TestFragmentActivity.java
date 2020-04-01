package com.alibaba.android.arouter.demo.testfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.alibaba.android.arouter.demo.R;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/test/test_fragment")
public class TestFragmentActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);

        RadioGroup radioGroup = findViewById(R.id.tab_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                    case R.id.tab_1:
                        showFragment((Fragment) ARouter.getInstance().build("/fragment/tab1")
                                .withString("name", "tab1")
                                .withString("parentName", "p1")
                                .withInt("age", 1)
                                .withInt("parentAge", 10)
                                .navigation());
                        break;
                    case R.id.tab_2:
                        showFragment((Fragment) ARouter.getInstance().build("/fragment/tab2")
                                .withString("name", "tab2")
                                .withString("parentName", "p2")
                                .withInt("age", 2)
                                .withInt("parentAge", 20)
                                .navigation());
                        break;
                    case R.id.tab_3:
                        showFragment((Fragment) ARouter.getInstance().build("/fragment/tab3")
                                .withString("name", "tab3")
                                .withString("parentName", "p3")
                                .withInt("age", 3)
                                .withInt("parentAge", 30)
                                .navigation());
                        break;
                    default:
                        showFragment((Fragment) ARouter.getInstance().build("/fragment/tabX")
                                .withString("name", "tabX")
                                .withString("parentName", "pX")
                                .withInt("age", 0)
                                .withInt("parentAge", 0)
                                .navigation());
                        break;
                }
            }
        });
    }

    private void showFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
