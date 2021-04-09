package com.cnc.xxrpc.client.stub;

import com.cnc.xxrpc.annotation.RpcStub;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/29 9:04 上午
 */
@RpcStub
public interface HelloService {
    String hello(String name, int age);
}
