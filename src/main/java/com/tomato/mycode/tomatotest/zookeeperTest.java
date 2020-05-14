package com.tomato.mycode.tomatotest;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

//curator框架实现zookeeper客户端开源实现
public class zookeeperTest {
    /**
     * Zookeeper info
     */
    private static final String ZK_ADDRESS = "192.168.137.1:2181";
    private static final String ZK_LOCK_PATH = "/zktest";

    private static void doWithLock(CuratorFramework client) {
        System.out.println(Thread.currentThread().getName() + "---------------doWithLock in");
        InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
        try {
            if (lock.acquire(10 * 1000, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "---------------hold lock");
                Thread.sleep(50000L);
                System.out.println(Thread.currentThread().getName() + "---------------release lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 1.Connect to zk
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        System.out.println("zk client start successfully!");
//        doWithLock(client);

//        Thread t1 = new Thread(() -> {
//            doWithLock(client);
//        }, "t1");
//        Thread t2 = new Thread(() -> {
//            doWithLock(client);
//        }, "t2");
//
//        t1.start();
//        t2.start();

        for (int i = 0; i < 2; i++) {
            String name = "thread_" + Integer.toString(i);
            Thread t = new Thread(() -> {
                doWithLock(client);
            }, name);
            t.start();
        }
    }
}
