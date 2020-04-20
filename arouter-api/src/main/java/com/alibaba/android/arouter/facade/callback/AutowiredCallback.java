package com.alibaba.android.arouter.facade.callback;

import java.util.List;

import androidx.annotation.NonNull;

public interface AutowiredCallback {
    void onAutowiredFailed(@NonNull List<String> fields);
}
