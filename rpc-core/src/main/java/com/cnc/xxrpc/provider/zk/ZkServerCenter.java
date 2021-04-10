package com.cnc.xxrpc.provider.zk;

import com.cnc.xxrpc.entity.XXServiceProperties;
import com.cnc.xxrpc.provider.ServerCenter;

import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 2:51 下午
 */
public class ZkServerCenter implements ServerCenter {



    @Override
    public Set<String> getAvailableServerAddresses(XXServiceProperties properties) {
        return null;
    }


}
