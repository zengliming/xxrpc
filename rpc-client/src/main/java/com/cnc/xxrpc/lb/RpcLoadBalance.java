package com.cnc.xxrpc.lb;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:05 下午
 */
public interface RpcLoadBalance {
    <T> T choose(Set<T> ts) throws Exception;
}
