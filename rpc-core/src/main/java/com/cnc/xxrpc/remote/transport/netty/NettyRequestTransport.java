package com.cnc.xxrpc.remote.transport.netty;

import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.dto.RpcResponse;
import com.cnc.xxrpc.remote.transport.RequestTransporter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 11:39 上午
 */
@Slf4j
public class NettyRequestTransport extends SimpleChannelInboundHandler<RpcResponse> implements RequestTransporter {
    private final Channel channel;


    public NettyRequestTransport(Channel channel) {
        this.channel = channel;
    }

    @Override
    public RpcResponse request(RpcRequest request) {
        channel.write("hello world");
        return null;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {

    }
}
