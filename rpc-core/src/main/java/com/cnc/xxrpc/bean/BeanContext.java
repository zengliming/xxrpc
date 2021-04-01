package com.cnc.xxrpc.bean;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tony
 * @desc 内部 bean 管理容器
 * @createDate 2021/3/28 11:58 上午
 */
@Slf4j
public class BeanContext {

    static final Map<Class<?>, BeanHolder<Object>> instanceCache = new ConcurrentHashMap<>();

    /**
     * get a bean from cache or create a new default
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clz) {
        if (clz == null) {
            throw new IllegalArgumentException("bean 类型不允许为空");
        }
        if (clz.isInterface()) {
            throw new IllegalArgumentException("接口类型不允许被实例化");
        }
        BeanHolder<Object> holder = instanceCache.get(clz);
        // 并发粒度控制
        if (holder == null) {
            BeanHolder<Object> newHolder = new BeanHolder<>();
            if (null == instanceCache.putIfAbsent(clz, newHolder)) {
                holder = instanceCache.get(clz);
            } else {
                holder = newHolder;
            }
        }
        // 从 holder中获取实际 bean-instance, 采用 Lock-On-Write 思路
        Class<Object> holderKey = (Class<Object>) clz;
        Object instance = holder.get(holderKey);
        if (null == instance) {
            synchronized (holder) {
                instance = holder.get(holderKey);
                if (null == instance) {
                    // TODO: 完成 clz 的安全实例化
//                    Constructor<T> constructor;
//                    try {
//                        constructor = clz.getDeclaredConstructor();
//                    } catch (NoSuchMethodException e) {
//                        log.error("Class {} don't has a default constructor", clz.getName());
//                        return null;
//                    }
//                    constructor.setAccessible(true);
                    try {
                        instance = clz.newInstance();
                        holder.set(instance);
                    } catch (InstantiationException | IllegalAccessException  e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return clz.cast(instance);
    }
}
