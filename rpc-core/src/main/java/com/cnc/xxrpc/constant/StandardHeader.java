package com.cnc.xxrpc.constant;

/**
 * @author tony
 * @desc 全局配置
 * @createDate 2021/3/29 8:59 下午
 */
public class StandardHeader {
    // 2 字节校验和
    public static final byte[] CHECK_SUM = new byte[]{(byte) 0xAB, (byte) 0xCD};
    // 主版本号
    public static final byte FIRSTLY_VERSION = 1;
    // 副版本号
    public static final byte SECONDARY_VERSION = 0;

    public static int getHeaderLength() {
        return CHECK_SUM.length + 2;
    }
}
