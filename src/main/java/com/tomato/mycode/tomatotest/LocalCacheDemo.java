package com.tomato.mycode.tomatotest;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LocalCacheDemo {
    private final Integer existFlag = new Integer(1);

    private LoadingCache<String, Integer> cache = null;

    public Integer get(String key) {
        return cache.getIfPresent(key);
    }

    public void put(String key, Integer value) {
        cache.put(key, value);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("main begin ......");

         LoadingCache<String, Integer> cache = CacheBuilder.newBuilder().recordStats().maximumSize(5000000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Integer>() {
                           @Override
                           public Integer load(String key) throws Exception {
                               System.out.println("key loadding ......");
                               return 22;
                           }
                       }
                );

        cache.put("hello", 11);
        Integer value = cache.getIfPresent("hello");
        System.out.println("value=" + value);
        Thread.sleep(5000);
//        cache.put("hello", 33);
         cache.get("hello");
        value = cache.getIfPresent("hello");
        System.out.println("value2=" + value);

        Thread.sleep(7000);
        cache.get("hello");
        value = cache.getIfPresent("hello");
        System.out.println("value3=" + value);

        System.out.println("main end ......");
    }
}
