package com.cnc.xxrpc.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author tony
 * @desc RPC 客户端存根注解
 * @createDate 2021/3/25 4:22 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {TYPE})
public @interface RpcStub {
}
