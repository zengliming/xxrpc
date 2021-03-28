package com.cnc.xxrpc.transport.netty.handler;

import com.cnc.xxrpc.codec.RpcProtocolCodec;
import com.cnc.xxrpc.compress.RpcCompressor;
import com.cnc.xxrpc.dto.RpcRequest;
import com.cnc.xxrpc.serialize.RpcSerializer;
import com.cnc.xxrpc.transport.netty.NettyRpcClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;


/**
 * @author tony
 * @desc 客户端请求服务端的出站处理, 当客户端进行 write 数据的时候发生
 * @createDate 2021/3/28 4:00 下午
 */
@Slf4j
public class NettyRequestHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        final RpcRequest request;
        NettyRpcClient client = (NettyRpcClient) ctx.channel().attr(AttributeKey.valueOf(NettyRpcClient.CLIENT_CTX)).get();
        try {
            request = (RpcRequest) msg;
        } catch (ClassCastException e) {
            log.error("无法将一个" + msg.getClass().getName() + "转换成一个" + RpcRequest.class.getName());
            return;
        }
        final RpcSerializer serializer = client.getSerializer();
        final RpcCompressor compressor = client.getCompressor();
        final RpcProtocolCodec codec = client.getProtocolCodec();
        byte[] sendMsg;
        try {
            sendMsg = codec.build(compressor.compress(serializer.serialize(request)));
        } catch (Exception e) {
            log.info(e.toString());
            return;
        }
        super.write(ctx, sendMsg, promise);
    }
}
