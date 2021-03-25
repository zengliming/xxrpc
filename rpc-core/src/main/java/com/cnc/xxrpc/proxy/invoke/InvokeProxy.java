package com.cnc.xxrpc.proxy.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:39 下午
 */
public class InvokeProxy implements InvocationHandler {
    private Class<?> clz;

    public InvokeProxy(Class<?> clz) {
        this.clz = clz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        String interfaceName = clz.getName();
        String methodName = method.getName();
        System.out.println(interfaceName);
        System.out.println(methodName);
        System.out.println("After invoke " + method.getName());
        return null;
    }


    public static <T> T wrapper(Class<T> clz) {
        InvocationHandler handler = new InvokeProxy(clz);
        return clz.cast(Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, handler));
    }
}
