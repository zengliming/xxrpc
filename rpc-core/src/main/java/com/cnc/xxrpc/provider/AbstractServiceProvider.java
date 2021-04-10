package com.cnc.xxrpc.provider;

import com.cnc.xxrpc.entity.XXService;
import com.cnc.xxrpc.entity.XXServiceProperties;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 11:14 上午
 */
public class AbstractServiceProvider implements XXProvider {

    @Override
    public XXService getService() {
        throw new NotImplementedException();
    }

    @Override
    public XXService getService(XXServiceProperties properties) {
        throw new NotImplementedException();
    }

    @Override
    public List<XXService> getServices() {
        throw new NotImplementedException();
    }

    @Override
    public List<XXService> getServices(XXServiceProperties properties) {
        throw new NotImplementedException();
    }

    @Override
    public void publish(XXService service) {
        throw new NotImplementedException();
    }

    @Override
    public void publish(XXService service, XXServiceProperties properties) {

    }

    @Override
    public XXProvider addService(Object service) {
        throw new NotImplementedException();
    }

    @Override
    public XXProvider addService(Object service, XXServiceProperties properties) {
        throw new NotImplementedException();
    }

    @Override
    public void publishBatch() {
        throw new NotImplementedException();
    }
}
