package com.alibaba.android.arouter.demo.test.bean;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.annotation.JSONField;

public class ParamObject {

    @JSONField
    public String name;
    @JSONField
    public String description;

    @SuppressWarnings("unused")
    public ParamObject() {
        name = "test";
        description = "test";
    }

    public ParamObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{\n" +
                "    name='" + name + '\'' +
                ", description='" + description + '\'' +
                "\n}";
    }
}
