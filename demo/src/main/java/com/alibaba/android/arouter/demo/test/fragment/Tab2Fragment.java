package com.alibaba.android.arouter.demo.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.demo.R;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;

@Route(path = "/app/fragment/tab2")
public class Tab2Fragment extends BaseTabFragment {
    @Autowired
    public String key1;

    public String key2;

    @Autowired
    public Serializable obj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ARouter.getInstance().inject(this);

        key2 = getArguments() != null ? getArguments().getString("key2") : null;

        String builder = "name=" + name + "\n" +
                "description=" + description + "\n" +
                "key1=" + key1 + "\n" +
                "key2=" + key2 + "\n" +
                "obj=" + obj + "\n";
        ((TextView) view.findViewById(R.id.content)).setText(builder);
    }
}
