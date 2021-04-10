package com.cnc.xxrpc.entity;

import lombok.Getter;
import lombok.Setter;

import java.net.SocketAddress;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 11:40 上午
 */
@Getter
@Setter
public class XXService {
    SocketAddress remoteAddress;
    String serviceName;
    String serviceGroup;
    String serviceVersion;
}
