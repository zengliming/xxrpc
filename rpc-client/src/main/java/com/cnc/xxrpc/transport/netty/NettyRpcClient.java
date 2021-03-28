package com.cnc.xxrpc.transport.netty;

import com.cnc.xxrpc.bean.BeanContext;
import com.cnc.xxrpc.codec.RpcProtocolCodec;
import com.cnc.xxrpc.compress.RpcCompressor;
import com.cnc.xxrpc.discovery.RpcServerDiscoverer;
import com.cnc.xxrpc.lb.impl.RandomRpcLoadBalance;
import com.cnc.xxrpc.lb.RpcLoadBalance;
import com.cnc.xxrpc.proxy.LookupProxy;
import com.cnc.xxrpc.serialize.RpcSerializer;
import com.cnc.xxrpc.transport.netty.handler.NettyRequestHandler;
import com.cnc.xxrpc.transport.netty.handler.NettyResponseHandler;
import com.cnc.xxrpc.util.ResourceReader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.*;


/**
 * @author tony
 * @desc 请求客户端
 * @createDate 2021/3/25 11:29 上午
 */
@Slf4j
public class NettyRpcClient {
    public static final String CLIENT_CTX = "CLIENT_CTX";
    private String version;
    private byte codec;
    private byte compress;

    private Properties properties;
    private Bootstrap bootstrap;
    private RpcLoadBalance rpcLoadBalance = new RandomRpcLoadBalance();
    private RpcProtocolCodec protocolCodec;
    private RpcCompressor compressor;
    private RpcSerializer serializer;

    // 考虑用 bean context 收容 NettyClient 单例

    /**
     * 获取一个全局单例, 由bean容器收容的 NettyRpcClient 实例
     *
     * @return
     */
    public static NettyRpcClient get() {
        return BeanContext.getBean(NettyRpcClient.class);
    }


    private NettyRpcClient() {
        loadProperties();
        // register center
        String zkAddress = properties.getProperty("zookeeper.address");
        int zkPort = Integer.parseInt(properties.getProperty("zookeeper.port"));
        RpcServerDiscoverer.setup(zkAddress, zkPort);


        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 添加出站处理, 出站处理处理顺序为 从后往前, 使用addFirst来保证代码层面看起来是顺序地
                        // 出站添加在出站添加入站前, 保证入 入站后能够被所有的出站handler捕捉到
                        pipeline.addFirst(new NettyRequestHandler());
                        // 添加入站处理, 与出站处理相反
                        pipeline.addLast(new NettyResponseHandler());
                    }
                }); //TODO: complete the handler
    }

    public <T> T getService(Class<T> clz) {
        // 这里需要动态, 每一个Service 可能对应不同的 远程地址;
        return clz.cast(Proxy.newProxyInstance(
                clz.getClassLoader(),
                new Class[]{clz},
                new LookupProxy(clz, this)));
    }

    private void loadProperties() {
        try {
            properties = ResourceReader.getResource("xxrpc.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RpcLoadBalance getRpcLoadBalance() {
        return rpcLoadBalance;
    }


    public String getVersion() {
        return version;
    }

    public byte getCodec() {
        return codec;
    }

    public byte getCompress() {
        return compress;
    }

    public Properties getProperties() {
        return properties;
    }

    public RpcProtocolCodec getProtocolCodec() {
        return protocolCodec;
    }

    public RpcCompressor getCompressor() {
        return compressor;
    }

    public RpcSerializer getSerializer() {
        return serializer;
    }
}
