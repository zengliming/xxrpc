package com.cnc.xxrpc.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/4/10 2:49 下午
 */
@Slf4j
public class ZkCuratorUtil {
    // TODO: 考虑线程安全问题
    private static CuratorFramework client = null;

    public static CuratorFramework getClient() {
        if (client != null && client.getState() == CuratorFrameworkState.STARTED) {
            return client;
        }
        Properties properties = FilePropertiesUtil.loadProperties("xxrpc.properties");
        String zkAddress = properties.getProperty("zookeeper.address");
        client = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .connectionTimeoutMs(2000)
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("xxrpc")
                .build();
        client.start();
        try {
            // wait 30s until connect to the zookeeper
            if (!client.blockUntilConnected(30, TimeUnit.SECONDS)) {
                throw new RuntimeException("Time out waiting to connect to ZK!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void createNode(String nodePath) {
        createNode(nodePath, false);
    }

    public static void createNode(String nodePath, boolean sequential) {
        CreateMode mode;
        if (sequential) {
            mode = CreateMode.PERSISTENT_SEQUENTIAL;
        } else {
            mode = CreateMode.PERSISTENT;
        }
        CuratorFramework client = getClient();
        try {
            if (client.checkExists().forPath(nodePath) != null) {
                log.info("node [{}] has already exists", nodePath);
            }
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(nodePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTempNode(String nodePath) {
        createTempNode(nodePath, false);
    }

    public static void createTempNode(String nodePath, boolean sequential) {
        CreateMode mode;
        if (sequential) {
            mode = CreateMode.EPHEMERAL_SEQUENTIAL;
        } else {
            mode = CreateMode.EPHEMERAL;
        }
        CuratorFramework client = getClient();
        try {
            if (client.checkExists().forPath(nodePath) != null) {
                log.info("node [{}] has already exists", nodePath);
            }
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(nodePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
