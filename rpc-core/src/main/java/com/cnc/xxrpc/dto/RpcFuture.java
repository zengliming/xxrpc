package com.cnc.xxrpc.dto;

import com.cnc.xxrpc.dto.RpcResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CompletableFuture;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:53 下午
 */
@Getter
@Setter
public class RpcFuture {
    private final CompletableFuture<RpcResponse> completableFuture;
    private boolean isActive = true;
    private Exception exc = null;

    public RpcFuture(CompletableFuture<RpcResponse> completableFuture) {
        this.completableFuture = completableFuture;
    }

    public RpcResponse get() throws Exception {
        if (exc != null) {
            throw exc;
        }
        return completableFuture.get();
    }
}
