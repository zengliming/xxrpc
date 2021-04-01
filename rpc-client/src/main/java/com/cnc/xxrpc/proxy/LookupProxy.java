package com.cnc.xxrpc.proxy;


import com.cnc.xxrpc.discovery.RpcServerDiscoverer;
import com.cnc.xxrpc.dto.RpcFuture;
import com.cnc.xxrpc.dto.RpcRequest;
import com.cnc.xxrpc.transport.ProcessingRequests;
import com.cnc.xxrpc.transport.netty.NettyRpcClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:39 下午
 */
@Slf4j
public class LookupProxy implements InvocationHandler {
    private final Class<?> clz;
    private final NettyRpcClient client;

    public LookupProxy(Class<?> clz, NettyRpcClient rpcClient) {
        this.clz = clz;
        this.client = rpcClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO: 完成基础信息注入
        RpcRequest rpcRequest = RpcRequest.builder()
                .requestID(123123123L)
                .className("")
                .interfaceName(method.getDeclaringClass().getSimpleName())
                .methodName(method.getName())
                .params(args)
                .paramTypes(method.getParameterTypes())
                .build();
        // 此处可以加入拦截器等
//        Channel channel = RpcServerDiscoverer.getServerChannel(clz); // TODO: 处理异常


        //考虑是否需要传递 clientCtx
//        channel.attr(AttributeKey.valueOf(NettyRpcClient.CLIENT_CTX)).set(client);
//        RpcFuture rpcFuture = new RpcFuture(new CompletableFuture<>());
//        // 这里同步阻塞
//
//        if (channel.isActive()) {
//            ProcessingRequests.put(rpcRequest.getRequestID(), rpcFuture);
//            channel.write(rpcRequest).addListener((ChannelFutureListener) future -> {
//                if (future.isSuccess()) {
//                    log.info(rpcRequest.toString()); //TODO: 格式化终端输出
//                } else {
//                    future.channel().close().sync();
//                    // 抛出异常入站返回
//                    rpcFuture.getCompletableFuture().completeExceptionally(future.cause());
//                    log.error(future.cause().toString()); // TODO: 格式化输出
//                }
//            });
//        }
//        return rpcFuture.get();

        Bootstrap bs = client.getBootstrap();
        log.info("get bootstrap successfully ...");
        Channel ch = bs.connect("127.0.0.1", 8099).sync().channel();
        log.info("get channel successfully ...");
        ch.writeAndFlush(rpcRequest);
        return null;
    }

}
