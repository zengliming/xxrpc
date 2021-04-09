package com.cnc.xxrpc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/25 11:25 上午
 */
@Getter
@Setter
@Slf4j
public class RpcResponse implements Serializable{
    private static final long serialVersionUID = -2524587347775862771L;

    // 64位 唯一请求号, 设计为了后续拓展与追踪
    long requestID;

    private Object data;
}
