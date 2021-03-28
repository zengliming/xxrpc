package com.cnc.xxrpc.proxy.lookup;


import com.cnc.xxrpc.governance.RpcServerDiscoverer;
import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.dto.RpcResponse;
import com.cnc.xxrpc.remote.transport.RequestTransporter;
import com.cnc.xxrpc.remote.transport.netty.NettyRequestTransport;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:39 下午
 */
public class LookupProxy implements InvocationHandler {
    private final Class<?> clz;
    private final Bootstrap bootstrap;

    public LookupProxy(Class<?> clz, Bootstrap bs) {
        this.clz = clz;
        this.bootstrap = bs;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        RpcRequest rpcRequest = new RpcRequest();
        // 此处可以加入拦截器等
        Channel channel = RpcServerDiscoverer.getServerChannel(clz);
        RequestTransporter transporter = new NettyRequestTransport(channel);
        RpcResponse response = transporter.request(rpcRequest);
        return response.getData();
    }

}
