package com.alibaba.android.arouter.demo.testfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.demo.R;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/fragment/tab2")
public class Tab2Fragment extends BaseTabFragment
{
    @Autowired
    String name;

    @Autowired
    int age;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        ARouter.getInstance().inject(this);

        TextView textView = view.findViewById(R.id.fragment_content);
        textView.setText(String.format("[0x%s]@{parent:%s,%s},{child:%s,%s}", Integer.toHexString(hashCode()), parentName, parentAge, name, age));
    }
}
