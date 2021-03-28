package com.cnc.xxrpc.remote.transport.netty;

import com.cnc.xxrpc.governance.RpcServerDiscoverer;
import com.cnc.xxrpc.proxy.lookup.LookupProxy;
import com.cnc.xxrpc.util.ResourceReader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.*;


/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:29 上午
 */
@Slf4j
public class NettyRpcClient {
    public static Properties properties;
    private static Bootstrap bootstrap;
    static {

        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(null); //TODO: complete the handler
    }
    public NettyRpcClient() {
        loadProperties();
        // register center
        String zkAddress = properties.getProperty("zookeeper.address");
        int zkPort = Integer.parseInt(properties.getProperty("zookeeper.port"));
        RpcServerDiscoverer.setup(zkAddress, zkPort);
    }

    public void setBootstrap(Bootstrap bs) {
        bootstrap = bs;
    }

    public <T> T getService(Class<T> clz) {
        // 这里需要动态, 每一个Service 可能对应不同的 远程地址;
        return clz.cast(Proxy.newProxyInstance(
                clz.getClassLoader(),
                new Class[]{clz},
                new LookupProxy(clz)));
    }

    synchronized public static void loadProperties() {
        try {
            properties = ResourceReader.getResource("xxrpc.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
