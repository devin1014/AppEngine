package com.alibaba.android.arouter.demo.test.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 *
 */
public interface ToastService extends IProvider {
    void toast(String message);
}
