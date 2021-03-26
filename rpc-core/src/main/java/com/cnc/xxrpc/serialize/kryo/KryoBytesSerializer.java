package com.cnc.xxrpc.serialize.kryo;

import com.cnc.xxrpc.exception.DeserializerException;
import com.cnc.xxrpc.exception.SerializerException;
import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.dto.RpcResponse;
import com.cnc.xxrpc.serialize.AbstractBytesSerializer;
import com.cnc.xxrpc.serialize.TestSerializeObject;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 1:59 下午
 */
public class KryoBytesSerializer extends AbstractBytesSerializer {

    // 确保不被多个线程获取到即可 TODO: 测试同一个线程内
    static final ThreadLocal<Kryo> threadLocalKryo = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcRequest.class);
        kryo.register(RpcResponse.class);
        // just for test
        kryo.register(TestSerializeObject.class);
        return kryo;
    });

    @Override
    public byte[] serialize(Object obj) {
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                Output output = new Output(outputStream)
        ) {
            Kryo kryo = threadLocalKryo.get();
            kryo.writeObject(output, obj); // 将对象写入到包装的输出流
            return output.toBytes();

        } catch (IOException e) {
            throw new SerializerException("Failed to serialize a " + obj.getClass().toString() + " object; cause by: " + e.getMessage());
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                Input input = new Input(inputStream)
        ) {
            Kryo kryo = threadLocalKryo.get();
            return kryo.readObject(input, clazz);
        } catch (IOException e) {
            throw new DeserializerException("Failed to deserialize a " + clazz.toString() + " object; cause by: " + e.getMessage());
        }
    }
}
