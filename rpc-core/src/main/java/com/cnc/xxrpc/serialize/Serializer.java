package com.cnc.xxrpc.serialize;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:15 上午
 */
public interface Serializer<E> {

    /**
     * 序列化
     */
    E serialize(Object from);

    /**
     * 反序列化
     */
    <T> T deserialize(E to, Class<T> clazz);
}
