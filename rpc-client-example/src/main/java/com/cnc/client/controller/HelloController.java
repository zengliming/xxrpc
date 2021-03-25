package com.cnc.client.controller;

import com.cnc.client.remote.HelloService;
import com.cnc.xxrpc.annotation.Registry;
import com.cnc.xxrpc.annotation.RpcController;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:03 下午
 */

@RpcController
public class HelloController {

    @Registry
    HelloService helloService;

    public void hello(String name, Integer age) {
        helloService.hello(name, age);
    }
}
