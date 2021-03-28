package com.cnc.xxrpc.util.zk;

import com.cnc.xxrpc.util.ServerCenter;
import io.netty.channel.Channel;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 2:51 下午
 */
public class ZkServerCenter implements ServerCenter {

    public ZkServerCenter(String address, int port) {
    }

    @Override
    public Set<String> getAvailableServerAddresses(Class<?> clz) {
        return null;
    }

    @Override
    public Set<Channel> getAvailableServers(Class<?> clz) {
        return null;
    }
}
