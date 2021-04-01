package com.cnc.xxrpc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:25 上午
 */
@Getter
@Setter
@Builder
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -2524587347775862771L;

    // 64位 唯一请求号, 设计为了后续拓展与追踪
    long requestID;

    // 指定接口的实现
    String interfaceName;
    // 指定实现类, 可选的
    String className;
    // 指定远程方法名称
    String methodName;

    // params: 参数必须有序, 所以用数组
    Object[] params;
    Class<?>[] paramTypes;
    

}
