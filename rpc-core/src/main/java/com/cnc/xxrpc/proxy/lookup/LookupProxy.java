package com.cnc.xxrpc.proxy.lookup;


import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.transport.netty.NettyRequestTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:39 下午
 */
public class LookupProxy implements InvocationHandler {
    private Class<?> clz;

    public LookupProxy(Class<?> clz) {
        this.clz = clz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        RpcRequest rpcRequest = new RpcRequest();


        // 请求代码封装: 将本地请求转发成一个 RPC 请求
        String interfaceName = clz.getName();
        String methodName = method.getName();
        System.out.println("After invoke " + method.getName());
        return interfaceName + methodName;
    }


    public static <T> T wrapper(Class<T> clz) {
        InvocationHandler handler = new LookupProxy(clz);
        return clz.cast(Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, handler));
    }
}
