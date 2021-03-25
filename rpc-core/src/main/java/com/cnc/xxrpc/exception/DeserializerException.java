package com.cnc.xxrpc.exception;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 2:44 下午
 */
public class DeserializerException extends RuntimeException {
    public DeserializerException(String message) {
        super(message);
    }
}
