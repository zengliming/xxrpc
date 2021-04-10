package com.cnc.xxrpc.manager;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 3:00 下午
 */
public interface Manager<T, E> {
    T get(E key);

    void put(E key, T resource);
}
