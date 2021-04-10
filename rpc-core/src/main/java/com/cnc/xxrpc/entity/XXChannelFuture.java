package com.cnc.xxrpc.entity;

import com.cnc.xxrpc.dto.XXResponse;
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
public class XXChannelFuture {
    private final CompletableFuture<XXResponse> completableFuture;
    private boolean isActive = true;
    private Exception exc = null;

    public XXChannelFuture(CompletableFuture<XXResponse> completableFuture) {
        this.completableFuture = completableFuture;
    }

    public XXResponse get() throws Exception {
        if (exc != null) {
            throw exc;
        }
        return completableFuture.get();
    }
}
