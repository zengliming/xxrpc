package com.cnc.xxrpc.codec;

import com.cnc.xxrpc.constant.StandardHeader;
import com.cnc.xxrpc.dto.RpcRequest;
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


    public RpcProtocolDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    public static RpcProtocolDecoder newInstance() {
        return new RpcProtocolDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    // 通过协议解析

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        log.info("decoder called...........");
        Object decoded = super.decode(ctx, in);
        if (decoded instanceof ByteBuf) {
            log.info("全包");
            ByteBuf frame = (ByteBuf) decoded;
            // 手动进行包解开
            if (frame.readableBytes() >= StandardHeader.getHeaderLength()) {
                try {
                    System.out.println(frame);
                } catch (Exception e) {
                    log.error("Decode frame error!", e);
                    throw e;
                } finally {
                    frame.release();
                }
            }

        }else{
            log.info("半包");
        }
        return decoded;
    }
}
