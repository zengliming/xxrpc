package com.cnc.xxrpc.remote.transport.netty;

import com.cnc.xxrpc.annotation.Registry;
import com.cnc.xxrpc.annotation.RpcController;
import com.cnc.xxrpc.annotation.RpcStub;
import com.cnc.xxrpc.proxy.invoke.InvokeProxy;
import com.cnc.xxrpc.remote.transport.RequestTransporter;
import com.cnc.xxrpc.util.ClassScanner;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:29 上午
 */
public class RpcClient implements RequestTransporter {

    public RpcClient(String... basePackage) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        for (String s : basePackage) {
            classes.addAll(ClassScanner.getClasses(s));
        }
        scan(classes);

    }

    public void scan(Set<Class<?>> classes) {
        for (Class<?> clz : classes) {
            if (clz.isAnnotationPresent(RpcController.class)) {
                Field[] fields = clz.getDeclaredFields();
                for (Field f : fields) {
                    if (f.isAnnotationPresent(Registry.class)) {
                        if (f.getType().isAnnotationPresent(RpcController.class)) {
                            try {
                                f.set(clz, InvokeProxy.wrapper(f.getType()));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void request() {

    }

    public void start() {

    }
}
