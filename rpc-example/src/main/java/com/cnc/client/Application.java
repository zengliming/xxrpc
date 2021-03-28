package com.cnc.client;

import com.cnc.client.remote.HelloService;
import com.cnc.xxrpc.remote.transport.netty.NettyRpcClient;
import lombok.extern.slf4j.Slf4j;


/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:01 下午
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        NettyRpcClient client = new NettyRpcClient();
        HelloService helloService = client.getService(HelloService.class);
        String res = helloService.hello("tony", 25);
        System.out.println(res);
    }
}
