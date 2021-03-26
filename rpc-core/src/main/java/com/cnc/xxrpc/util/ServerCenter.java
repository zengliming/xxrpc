package com.cnc.xxrpc.util;

import io.netty.channel.Channel;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 2:48 下午
 */
public interface ServerCenter {

    Set<String> getAvailableServerAddresses(Class<?> clz);

    Set<Channel> getAvailableServers(Class<?> clz);
}
