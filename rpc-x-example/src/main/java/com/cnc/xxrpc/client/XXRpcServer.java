package com.cnc.xxrpc.client;

import com.cnc.xxrpc.codec.RpcProtocolDecoder;
import com.cnc.xxrpc.dto.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author tony
 * @desc 测试服务端
 * @createDate 2021/3/30 8:10 下午
 */
@Slf4j
public class XXRpcServer {
    public void run(int port) throws InterruptedException {
        EventLoopGroup scheduler = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bs = new ServerBootstrap();
            bs
                    .group(scheduler, worker)
                    .localAddress(new InetSocketAddress(port))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", RpcProtocolDecoder.newInstance());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            log.info("服务启动成功, 监听端口为: {} ...", port);
            ChannelFuture future = bs.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            scheduler.shutdownGracefully().sync();
            worker.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        XXRpcServer sv = new XXRpcServer();
        try {
            sv.run(8099);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
