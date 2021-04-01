package com.cnc.xxrpc.serialize.kryo;

import com.cnc.xxrpc.serialize.TestSerializeObject;
import com.cnc.xxrpc.serialize.RpcSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KryoBytesSerializerTest {


    private static RpcSerializer serializer;

    @BeforeEach
    public void tearUp() {
        serializer = new KryoBytesSerializer();
    }


    @Test
    public void testSerialize() {
        TestSerializeObject d = new TestSerializeObject();
        byte[] bs = serializer.serialize(d);
        assertTrue(bs.length > 0);
    }

    @Test
    public void testDeserialize() {
        TestSerializeObject d = new TestSerializeObject();
        byte[] bs = serializer.serialize(d);
        TestSerializeObject demo = serializer.deserialize(bs, TestSerializeObject.class);
        System.out.println(demo);

    }

}