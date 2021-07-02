package com.alibaba.android.arouter.app.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * Used for json converter
 *
 * @author zhilong <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2017/4/10 下午2:10
 */
@Route(path = "/app/service/object_parse")
public class ObjectParseServiceImpl implements SerializationService {
    @Override
    public void init(Context context) {
        ARouter.logger.info(getClass().getSimpleName(), getClass().getSimpleName() + " has init.");
    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return JSON.toJSONString(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return JSON.parseObject(input, clazz);
    }
}
