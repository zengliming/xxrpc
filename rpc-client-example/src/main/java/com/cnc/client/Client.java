package com.cnc.client;

import com.cnc.client.controller.HelloController;
import com.cnc.xxrpc.remote.transport.netty.RpcClient;


/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 4:01 下午
 */
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient("com.cnc.client");
        HelloController controller = new HelloController();
        controller.hello("tony", 21);
    }
}
