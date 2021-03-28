package com.cnc.xxrpc.transport.netty.handler;

import com.cnc.xxrpc.dto.RpcResponse;
import com.cnc.xxrpc.transport.ProcessingRequests;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tony
 * @desc 客户端的入站拦截处理, 当客户端收到服务端响应数据的时候发生
 * @createDate 2021/3/28 3:58 下午
 */
@Slf4j
public class NettyResponseHandler extends SimpleChannelInboundHandler<RpcResponse> {

    //消息可读处理
    @Override
    public void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        // 往future 中写东西
        try {
            ProcessingRequests.finish(msg);
        } catch (Exception e) {
            // TODO: 考虑是否可以在这里加入降级操作
            log.error(e.getMessage());
        }
        // 释放资源
        ReferenceCountUtil.release(msg);
    }

    // 客户端心跳
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }


}
