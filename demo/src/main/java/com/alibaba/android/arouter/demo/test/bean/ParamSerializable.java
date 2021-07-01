package com.alibaba.android.arouter.demo.test.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ParamSerializable implements Serializable {

    public String name;
    public String description;

    @SuppressWarnings("unused")
    public ParamSerializable() {
        name = "test";
        description = "test";
    }

    public ParamSerializable(String name, String description) {
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
