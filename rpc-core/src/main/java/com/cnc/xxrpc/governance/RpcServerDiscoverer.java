package com.cnc.xxrpc.governance;

import com.cnc.xxrpc.annotation.RpcStub;
import com.cnc.xxrpc.proxy.lookup.LookupProxy;
import com.cnc.xxrpc.util.ServerCenter;
import com.cnc.xxrpc.util.zk.ZkServerCenter;
import io.netty.channel.Channel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 10:16 上午
 */
public class RpcServerDiscoverer {
    private static int PULL_CYCLE_DELAY = 60;

    private static final Map<Class<?>, Set<String>> SERVER_ADDRESS_MAP = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Set<? extends Channel>> SERVER_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static ServerCenter serverCenter;

    /**
     * 定时拉取任务, 每隔 n 时间拉取远程的可用服务列表
     *
     * @param clz
     */
    public static void pullingTask(Class<?> clz) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                synchronized (clz) {
                    SERVER_ADDRESS_MAP.put(clz, serverCenter.getAvailableServerAddresses(clz));
                    SERVER_CHANNEL_MAP.put(clz, serverCenter.getAvailableServers(clz));
                }
            }
        };
        // 每隔 60s 进行一次最新的 服务中心拉取
        timer.schedule(timerTask, PULL_CYCLE_DELAY);
    }

    public static void setup(String address, int port) {
        serverCenter = new ZkServerCenter(address, port);
    }


    public static String getServerAddress(Class<?> clz) {
        // TODO: loadBalance
        return null;
    }


    public static Channel getServerChannel(Class<?> clz) {
        // TODO: loadBalance
        return null;
    }

    public static <T> T lookup(Class<T> clz) {
        if (!clz.isInterface()) {
            throw new IllegalArgumentException("clz must be a interface");
        }
        if (!clz.isAnnotationPresent(RpcStub.class)) {
            throw new IllegalArgumentException("remote server must be RpcStub annotation presented");
        }
        return LookupProxy.wrapper(clz);
    }
}
