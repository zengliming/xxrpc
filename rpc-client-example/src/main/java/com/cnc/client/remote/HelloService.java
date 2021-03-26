package com.cnc.client.remote;

import com.cnc.xxrpc.annotation.RpcStub;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:08 下午
 */
@RpcStub
public interface HelloService {

    String hello(String name, int age);
}
