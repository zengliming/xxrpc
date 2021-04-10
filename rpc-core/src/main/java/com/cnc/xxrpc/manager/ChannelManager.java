package com.cnc.xxrpc.manager;

import io.netty.channel.Channel;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 3:01 下午
 */
public class ChannelManager implements Manager<Channel,String>{
    @Override
    public Channel get(String key) {
        return null;
    }

    @Override
    public void put(String key, Channel resource) {

    }
}
