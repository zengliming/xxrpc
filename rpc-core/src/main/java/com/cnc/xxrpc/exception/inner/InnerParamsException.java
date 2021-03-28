package com.cnc.xxrpc.exception.inner;

/**
 * @author tony
 * @desc 内部参数检验异常
 * @createDate 2021/3/28 12:12 下午
 */
public class InnerParamsException extends Exception {
    public InnerParamsException(String message) {
        super(message);
    }
}
