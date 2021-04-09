package com.cnc.xxrpc.codec;

import com.cnc.xxrpc.bean.BeanContext;
import com.cnc.xxrpc.compress.RpcCompressor;
import com.cnc.xxrpc.compress.impl.GzipCompressor;
import com.cnc.xxrpc.constant.StandardHeader;
import com.cnc.xxrpc.serialize.RpcSerializer;
import com.cnc.xxrpc.serialize.kryo.KryoBytesSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;


/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/29 8:15 下午
 */
@Slf4j
public class RpcProtocolEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

        RpcSerializer serializer = new KryoBytesSerializer();
        RpcCompressor compressor = BeanContext.getBean(GzipCompressor.class);
        // 对payload 进行序列化和压缩编码
        byte[] body = new byte[]{};
        try {
            byte[] s = serializer.serialize(msg);
            body = compressor.compress(s);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        ByteBuf sendBuf = Unpooled.buffer();

        sendBuf.writeBytes(StandardHeader.CHECK_SUM);
        sendBuf.writeByte(StandardHeader.FIRSTLY_VERSION);
        sendBuf.writeByte(StandardHeader.SECONDARY_VERSION);
        // 写入长度字符
        sendBuf.writeInt(StandardHeader.getHeaderLength() + body.length);
        //写入消息体
        sendBuf.writeBytes(body);
        // 发送消息到远程
        ctx.writeAndFlush(sendBuf);
    }
}
