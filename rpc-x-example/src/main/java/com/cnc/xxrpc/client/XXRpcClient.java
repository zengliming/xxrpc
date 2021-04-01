package com.cnc.xxrpc.client;

import com.cnc.xxrpc.client.stub.HelloService;
import com.cnc.xxrpc.transport.netty.NettyRpcClient;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 3:49 下午
 */
public class Application {

    public static void main(String[] args) {
        // Config by properties file
        HelloService service = NettyRpcClient.getService(HelloService.class);
        String result = service.hello("tony", 21);
    }
}
