package com.cnc.xxrpc.transport.netty;

import com.cnc.xxrpc.dto.RpcFuture;
import com.cnc.xxrpc.dto.RpcRequest;
import com.cnc.xxrpc.transport.ProcessingRequests;
import com.cnc.xxrpc.transport.RequestTransporter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 11:39 上午
 */
@Slf4j
public class NettyRequestTransport implements RequestTransporter {
    private final Channel channel;


    public NettyRequestTransport(Channel channel) {
        this.channel = channel;
    }

    // TODO: 处理客户端未发送请求超时问题
    @Override
    public RpcFuture request(RpcRequest request) {
        RpcFuture rpcFuture = new RpcFuture(new CompletableFuture<>());
        if (channel.isActive()) {
            ProcessingRequests.put(request.getRequestID(), rpcFuture);
            channel.writeAndFlush(request).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info(request.toString()); //TODO: 格式化终端输出
                } else {
                    future.channel().close().sync();
                    // 抛出异常入站返回
                    rpcFuture.getCompletableFuture().completeExceptionally(future.cause());
                    log.error(future.cause().toString()); // TODO: 格式化输出
                }
            });
        }
        return rpcFuture;
    }

}
