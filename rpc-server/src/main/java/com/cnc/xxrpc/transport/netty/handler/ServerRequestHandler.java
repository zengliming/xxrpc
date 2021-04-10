package com.cnc.xxrpc.transport.netty.handler;

import com.cnc.xxrpc.dto.XXRequest;
import com.cnc.xxrpc.dto.XXResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 10:40 上午
 */
@Slf4j
public class ServerRequestHandler extends SimpleChannelInboundHandler<XXRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, XXRequest msg) throws Exception {
        log.info("开始处理消息");
        Object result = handleRequest(msg);
        XXResponse response = new XXResponse();
        response.setRequestID(msg.getRequestID());
        response.setData(result);
        ctx.writeAndFlush(response);
    }


    private Object handleRequest(XXRequest msg) {
        return "this is a response";
    }
}
