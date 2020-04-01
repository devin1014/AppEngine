package com.alibaba.android.arouter.core;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.LruCache;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.AutowiredCallback;
import com.alibaba.android.arouter.facade.callback.SyringeCallback;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.template.ISyringe;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.android.arouter.utils.Consts.SUFFIX_AUTOWIRED;

/**
 * Autowired service impl.
 *
 * @author zhilong <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2017/2/28 下午6:08
 */
@Route(path = "/arouter/service/autowired")
public class AutowiredServiceImpl implements AutowiredService {
    private LruCache<String, ISyringe> classCache;
    private List<String> blackList;

    @Override
    public void init(Context context) {
        classCache = new LruCache<>(66);
        blackList = new ArrayList<>();
    }

    @Override
    public void autowire(Object instance, AutowiredCallback callback, @Nullable Class<?>... inheritClass) {
        final List<String> fields = new ArrayList<>();
        SyringeCallback syringeCallback = callback==null? null: new SyringeCallback() {
            @Override
            public void onSyringeFailed(String fieldName) {
                fields.add(fieldName);
            }
        };
        if(inheritClass==null || inheritClass.length==0){
            autowireObject(instance, syringeCallback, instance.getClass());
        }
        else{
            for(Class<?> clazz : inheritClass){
                autowireObject(instance, syringeCallback, clazz);
            }
        }
        if(fields.size()>0&&callback!=null) {
            callback.onAutowiredFailed(fields);
        }
    }

    private void autowireObject(Object instance, SyringeCallback callback, Class<?> clazz) {
        String className = clazz.getName();
        try {
            if (!blackList.contains(className)) {
                ISyringe autowiredHelper = classCache.get(className);
                if (null == autowiredHelper) {  // No cache.
                    autowiredHelper = (ISyringe) Class.forName(className + SUFFIX_AUTOWIRED).getConstructor().newInstance();
                }
                autowiredHelper.inject(instance,callback);
                classCache.put(className, autowiredHelper);
            }
        } catch (Exception ex) {
            blackList.add(className);    // This instance need not autowired.
        }
    }
}
