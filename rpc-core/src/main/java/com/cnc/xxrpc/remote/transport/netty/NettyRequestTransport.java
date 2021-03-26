package com.cnc.xxrpc.remote.transport.netty;

import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.dto.RpcResponse;
import com.cnc.xxrpc.remote.transport.RequestTransporter;
import io.netty.channel.Channel;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 11:39 上午
 */
public class NettyRequestTransport implements RequestTransporter {
    private final Channel channel;

    public NettyRequestTransport(Channel channel) {
        this.channel = channel;
    }

    @Override
    public RpcResponse request(RpcRequest request) {
        channel.write("hello world");
        
        return null;
    }
}
