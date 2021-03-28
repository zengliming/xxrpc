package com.cnc.xxrpc.compress;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 5:29 下午
 */
public interface RpcCompressor {

    byte[] compress(byte[] bytes);

    byte[] decompress(byte[] bytes);
}
