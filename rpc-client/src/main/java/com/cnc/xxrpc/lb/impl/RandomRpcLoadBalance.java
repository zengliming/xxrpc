package com.cnc.xxrpc.lb.impl;

import com.cnc.xxrpc.lb.RpcLoadBalance;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:08 下午
 */
public class RandomRpcLoadBalance implements RpcLoadBalance {

    @Override
    public <T> T choose(Set<T> ts) throws Exception {
        return null;
    }
}
