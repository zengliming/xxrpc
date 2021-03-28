package com.cnc.xxrpc.l.impl;

import com.cnc.xxrpc.lb.LoadBalance;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:08 下午
 */
public class RandomRpcLoadBalance implements LoadBalance {

    @Override
    public <T> T choose(Set<T> ts) throws Exception {
        return null;
    }
}
