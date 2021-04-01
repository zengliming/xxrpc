package com.cnc.xxrpc.transport.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;


/**
 * @author tony
 * @desc 从客户上下文中获取一份协议进行处理
 * @createDate 2021/3/28 4:00 下午
 */
@Slf4j
public class NettyRequestHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        final RpcRequest request;
//        try {
//            request = (RpcRequest) msg;
//        } catch (ClassCastException e) {
//            log.error("无法将一个" + msg.getClass().getName() + "转换成一个" + RpcRequest.class.getName());
//            return;
//        }
        log.info("request handler called...........");
        //TODO: checkout the request
        super.write(ctx, msg, promise);
    }
}
