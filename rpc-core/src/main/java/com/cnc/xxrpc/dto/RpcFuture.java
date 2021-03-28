package com.cnc.xxrpc.remote.dto;

import com.cnc.xxrpc.dto.RpcResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/28 2:53 下午
 */
public class RpcFuture {
    private CompletableFuture<RpcResponse> completableFuture;
    private boolean isActive;
    private Exception exc = null;

    public RpcResponse get() throws Exception {
        if (exc != null) {
            throw exc;
        }
        return completableFuture.get();
    }

    public void setCompletableFuture(CompletableFuture<RpcResponse> completableFuture) {
        this.completableFuture = completableFuture;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }
}
