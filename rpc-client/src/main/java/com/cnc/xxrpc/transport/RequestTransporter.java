package com.cnc.xxrpc.remote.transport;

import com.cnc.xxrpc.remote.dto.RpcFuture;
import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.dto.RpcResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:22 上午
 */
public interface RequestTransporter {
    RpcFuture request(RpcRequest request);
}
