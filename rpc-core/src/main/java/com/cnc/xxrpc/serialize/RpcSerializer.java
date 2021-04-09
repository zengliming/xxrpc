package com.cnc.xxrpc.serialize;

import com.cnc.xxrpc.exception.DeserializerException;
import com.cnc.xxrpc.exception.SerializerException;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:15 上午
 */
public interface RpcSerializer {

    /**
     * 序列化
     */
    byte[] serialize(Object from) throws SerializerException;

    /**
     * 反序列化
     */
    <T> T deserialize(byte[] to, Class<T> clazz) throws DeserializerException;
}
