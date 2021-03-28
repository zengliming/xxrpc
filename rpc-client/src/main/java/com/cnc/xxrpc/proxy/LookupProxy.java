package com.cnc.xxrpc.proxy;


import com.cnc.xxrpc.discovery.RpcServerDiscoverer;
import com.cnc.xxrpc.dto.RpcFuture;
import com.cnc.xxrpc.dto.RpcRequest;
import com.cnc.xxrpc.transport.RequestTransporter;
import com.cnc.xxrpc.transport.netty.NettyRequestTransport;
import com.cnc.xxrpc.transport.netty.NettyRpcClient;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:39 下午
 */
public class LookupProxy implements InvocationHandler {
    private final Class<?> clz;
    private final NettyRpcClient client;

    public LookupProxy(Class<?> clz, NettyRpcClient rpcClient) {
        this.clz = clz;
        this.client = rpcClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        // TODO: 完成基础信息注入
        RpcRequest rpcRequest = RpcRequest.builder().build();
        // 此处可以加入拦截器等
        Channel channel = RpcServerDiscoverer.getServerChannel(clz); // TODO: 处理异常
        channel.attr(AttributeKey.valueOf(NettyRpcClient.CLIENT_CTX)).set(client);
        RequestTransporter transporter = new NettyRequestTransport(channel);
        RpcFuture future = transporter.request(rpcRequest);
        return future.get();
    }

}
