package com.cnc.xxrpc.l;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:05 下午
 */
public interface LoadBalance {
    <T> T choose(Set<T> ts) throws Exception;
}
