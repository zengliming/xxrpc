package com.cnc.xxrpc.remote.transport.netty;

import com.cnc.xxrpc.annotation.Registry;
import com.cnc.xxrpc.annotation.RpcController;
import com.cnc.xxrpc.governance.RpcServerDiscoverer;
import com.cnc.xxrpc.proxy.lookup.LookupProxy;
import com.cnc.xxrpc.util.ResourceReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:29 上午
 */
@Slf4j
public class NettyRpcClient {
    public static Properties properties;

    public NettyRpcClient() {
        loadProperties();
        // register center
        String zkAddress = properties.getProperty("zookeeper.address");
        int zkPort = Integer.parseInt(properties.getProperty("zookeeper.port"));
        RpcServerDiscoverer.setup(zkAddress, zkPort);
    }

    synchronized public static void loadProperties() {
        try {
            properties = ResourceReader.getResource("xxrpc.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scan(String... basePackages) {
        Class<?>[] classes = new Class[]{Class.class}; // TODO: finish it

        for (Class<?> clz : classes) {
            if (clz.isAnnotationPresent(RpcController.class)) {
                Field[] fields = clz.getDeclaredFields();
                for (Field f : fields) {
                    if (f.isAnnotationPresent(Registry.class)) {
                        if (f.getType().isAnnotationPresent(RpcController.class)) {
                            try {
                                f.set(clz, LookupProxy.wrapper(f.getType()));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
