package com.cnc.xxrpc.codec;

import com.cnc.xxrpc.util.BeanContext;
import com.cnc.xxrpc.compress.RpcCompressor;
import com.cnc.xxrpc.compress.impl.GzipCompressor;
import com.cnc.xxrpc.serialize.RpcSerializer;
import com.cnc.xxrpc.serialize.kryo.KryoBytesSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tony
 * <p>
 * 自定义了一套协议:
 * 0           2              3                 4            8
 * +-----------+--------------+-----------------+------------+
 * | check sum | first version|secondary version|length field|
 * +---------------------------------------------------------+
 * |                                                         |
 * |                 message body                            |
 * |                                                         |
 * +---------------------------------------------------------+
 * @createDate 2021/3/29 9:13 上午
 */
@Slf4j
public class RpcProtocolDecoder extends LengthFieldBasedFrameDecoder {

    private static final int MAX_FRAME_LENGTH = 1024 * 2;
    private static final int LENGTH_FIELD_OFFSET = 4;
    private static final int LENGTH_FIELD_LENGTH = 4;
    private final Class<?> deserializeClz;

    public RpcProtocolDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, Class<?> clz) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        deserializeClz = clz;
    }

    public static RpcProtocolDecoder newInstance(Class<?> clz) {
        return new RpcProtocolDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, clz);
    }

    // 通过协议解析
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到一条消息, 接下来进行字节消息的解码... ");
        ByteBuf in = (ByteBuf) msg;
        byte[] body;
        if (in.readableBytes() > 16) {
            byte[] checkSum = new byte[2];
            in.readBytes(checkSum);
            if (checkSum[0] != (byte) 0xAB || checkSum[1] != (byte) 0xCD) {
                log.error("数据和校验不通过");
                return;
            }
            log.info("校验和校验通过");
            byte mainVersion = in.readByte();
            byte childVersion = in.readByte();
            log.info("消息版本号: {},{}", mainVersion, childVersion);

            int length = in.readInt();
            log.info("数据包的总长为: {}", length);
            body = new byte[in.readableBytes()];
            in.readBytes(body);

            RpcSerializer serializer = new KryoBytesSerializer();
            RpcCompressor compressor = BeanContext.getBean(GzipCompressor.class);

            // 进行多态分解
            Object bodyInstance = serializer.deserialize(compressor.decompress(body), deserializeClz);
            System.out.println(bodyInstance);
            log.info("解析对象成功");
            ctx.fireChannelRead(bodyInstance);
        }
    }
}
