package com.cnc.xxrpc.provider;

import com.cnc.xxrpc.entity.XXServiceProperties;
import io.netty.channel.Channel;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 2:48 下午
 */
public interface ServerCenter {

    Set<String> getAvailableServerAddresses(XXServiceProperties properties);
}
