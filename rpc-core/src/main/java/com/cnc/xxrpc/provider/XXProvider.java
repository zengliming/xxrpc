package com.cnc.xxrpc.provider;

import com.cnc.xxrpc.entity.XXService;
import com.cnc.xxrpc.entity.XXServiceProperties;

import java.util.List;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 11:07 上午
 */
public interface XXProvider {
    XXService getService();

    XXService getService(XXServiceProperties properties);

    List<XXService> getServices();

    List<XXService> getServices(XXServiceProperties properties);

    void publish(XXService service);

    void publish(XXService service, XXServiceProperties properties);

    XXProvider addService(Object service);

    XXProvider addService(Object service, XXServiceProperties properties);

    void publishBatch();

}
