package com.cnc.xxrpc.transport;


import com.cnc.xxrpc.entity.XXChannelFuture;
import com.cnc.xxrpc.dto.XXResponse;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:59 下午
 */
public class ProcessingRequests {
    private static final ConcurrentHashMap<Long, XXChannelFuture> processingRpcFuture = new ConcurrentHashMap<>();

    public static void put(Long requestId, XXChannelFuture future) {
        processingRpcFuture.put(requestId, future);
    }

    public static void finish(XXResponse rpcResponse) {
        XXChannelFuture rpcFuture = processingRpcFuture.remove(rpcResponse.getRequestID());
        if (null != rpcFuture) {
            // 完成响应工作
            rpcFuture.getCompletableFuture().complete(rpcResponse);
        } else {
            // 超时响应或其他
            throw new UnknownError("超时响应或其他异常");
        }
    }
}
