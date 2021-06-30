package com.alibaba.android.arouter.demo.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ParamObject implements Serializable, Parcelable {

    public static final Creator<ParamObject> CREATOR = new Creator<ParamObject>() {
        @Override
        public ParamObject createFromParcel(Parcel in) {
            return new ParamObject(in);
        }

        @Override
        public ParamObject[] newArray(int size) {
            return new ParamObject[size];
        }
    };

    public final String name;
    public final String description;

    @SuppressWarnings("unused")
    public ParamObject() {
        name = "test";
        description = "test";
    }

    public ParamObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ParamObject(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }

    @NonNull
    @Override
    public String toString() {
        return "ParamObject{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
