package com.cnc.xxrpc.bean;

/**
 * @author tony
 * @desc bean 容器持有器, 内存屏障设计保证 及时更新; TODO: 考虑是否需要线程安全保证
 * @createDate 2021/3/28 11:58 上午
 */
public class BeanHolder<T> {
    private volatile T value;

    public T get(Class<T> clz) {
        return value;
    }

    public void set(T tv) {
        value = tv;
    }
}
