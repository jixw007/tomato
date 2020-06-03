package com.tomato.mycode.tomatotest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static Integer integerCount = 0;

    private static void printinfo(int step) {
        System.out.printf("ThreadId=%d,ThreadName=%s, step=%d\n", Thread.currentThread().getId(), Thread.currentThread().getName(), step);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicIntegerCount = new AtomicInteger(0);

        System.out.printf("begin  integerCount=%d, atomicInteger=%d\n", integerCount, atomicIntegerCount.get());

        final CountDownLatch latch = new CountDownLatch(10000);

        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    //atomicIntegerCount.incrementAndGet();
                    //integerCount++;

                    int step = 0;
                    for (int j = 0; j < 1000; j++) {
                        step++;
                        printinfo(step);
                        //System.out.printf("ThreadId=%d,ThreadName=%s, integerCount=%d, atomicInteger=%d\n", Thread.currentThread().getId(), Thread.currentThread().getName(), integerCount, atomicIntegerCount.get());
                        latch.countDown();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t1.start();
        }

        latch.await();
        System.out.printf("end  integerCount=%d, atomicInteger=%d\n", integerCount, atomicIntegerCount.get());
    }
}
