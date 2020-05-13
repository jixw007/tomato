package com.tomato.mycode.tomatotest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public volatile static Integer integerCount = 0;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicIntegerCount = new AtomicInteger(0);
//        atomicInteger.incrementAndGet();
//        System.out.printf("atomicInteger=%d\n",atomicInteger.get());

        System.out.printf("begin  integerCount=%d, atomicInteger=%d\n", integerCount, atomicIntegerCount.get());

        final CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    atomicIntegerCount.incrementAndGet();
                    integerCount++;
                    System.out.printf("Thread  integerCount=%d, atomicInteger=%d\n", integerCount, atomicIntegerCount.get());
                    latch.countDown();
                }
            });
            t1.start();
        }

        latch.await();
        System.out.printf("end  integerCount=%d, atomicInteger=%d\n", integerCount, atomicIntegerCount.get());
    }
}
