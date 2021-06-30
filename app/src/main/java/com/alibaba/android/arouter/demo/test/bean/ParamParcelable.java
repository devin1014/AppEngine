package com.alibaba.android.arouter.demo.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.annotation.JSONField;

public class ParamParcelable implements Parcelable {

    public static final Creator<ParamParcelable> CREATOR = new Creator<ParamParcelable>() {
        @Override
        public ParamParcelable createFromParcel(Parcel in) {
            return new ParamParcelable(in);
        }

        @Override
        public ParamParcelable[] newArray(int size) {
            return new ParamParcelable[size];
        }
    };

    @JSONField
    public String name;
    @JSONField
    public String description;

    @SuppressWarnings("unused")
    public ParamParcelable() {
        name = "test";
        description = "test";
    }

    public ParamParcelable(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ParamParcelable(Parcel in) {
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
        return getClass().getSimpleName() + "{\n" +
                "    name='" + name + '\'' +
                ", description='" + description + '\'' +
                "\n}";
    }
}
