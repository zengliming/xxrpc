package com.cnc.xxrpc.remote;


import com.cnc.xxrpc.dto.RpcFuture;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:59 下午
 */
public class ProcessingRequests {
    private static final ConcurrentHashMap<Long, RpcFuture> processingRpcFuture = new ConcurrentHashMap<>();

}
