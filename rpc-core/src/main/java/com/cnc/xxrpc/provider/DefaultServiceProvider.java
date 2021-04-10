package com.cnc.xxrpc.provider;

import com.cnc.xxrpc.entity.XXService;
import com.cnc.xxrpc.entity.XXServiceProperties;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Set;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 11:24 上午
 */
public class DefaultServiceProvider extends AbstractServiceProvider {
    private static final XXServiceProperties defaultProperties = XXServiceProperties
            .builder()
            .group("default")
            .version("1.1.0")
            .name("default")
            .build();

    private final ServerCenter serverCenter;

    public DefaultServiceProvider(ServerCenter serverCenter) {
        this.serverCenter = serverCenter;
    }

    @Override
    public XXService getService() {
        return getService(defaultProperties);
    }

    @Override
    public XXService getService(XXServiceProperties properties) {
        Set<String> connectInfo = serverCenter.getAvailableServerAddresses(properties);

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
