package com.alibaba.android.arouter.facade.callback;

import java.util.List;

public interface AutowiredCallback {
    void onAutowiredFailed(List<String> fields);
}
