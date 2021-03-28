package com.cnc.xxrpc.transport;

import com.cnc.xxrpc.dto.RpcFuture;
import com.cnc.xxrpc.dto.RpcRequest;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:22 上午
 */
public interface RequestTransporter {
    RpcFuture request(RpcRequest request);
}
