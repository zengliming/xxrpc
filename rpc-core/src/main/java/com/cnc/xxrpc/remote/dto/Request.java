package com.cnc.xxrpc.remote.dto;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:25 上午
 */
public class Request<T> {

    // 64位 唯一请求号, 设计为了后续拓展与追踪
    long requestID;
    // 系统版本
    String version;
    //
    String interfaceName;
    String className;
    String methodName;

    // params: 参数必须有序, 所以用数组
    Object[] params;
    Class<?>[] paramTypes;

}
