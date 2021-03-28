package com.cnc.xxrpc.codec;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 5:29 下午
 */
public interface RpcProtocolCodec {

    byte[] build(byte[] bytes);


    byte[] parse(byte[] bytes);
}
