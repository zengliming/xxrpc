package com.cnc.xxrpc.remote.transport.netty;

import com.cnc.xxrpc.remote.dto.RpcFuture;
import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.dto.RpcResponse;
import com.cnc.xxrpc.remote.transport.RequestTransporter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
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

        channel.write("hello world");
        return null;
    }

}
