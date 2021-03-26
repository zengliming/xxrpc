package com.cnc.xxrpc.remote.transport;

import com.cnc.xxrpc.remote.dto.RpcRequest;
import com.cnc.xxrpc.remote.dto.RpcResponse;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:22 上午
 */
public interface RequestTransporter {
    RpcResponse request(RpcRequest request);
}
