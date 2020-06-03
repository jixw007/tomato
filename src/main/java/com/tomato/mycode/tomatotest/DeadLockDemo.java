package com.tomato.mycode.tomatotest;


public class DeadLockDemo {

    private static Object obj1 = new Object();//锁对象1
    private static Object obj2 = new Object();//锁对象2

    private static void runLock(int flag) {
        if (flag == 1) {
            //线程1执行代码
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + "已经获取到资源obj1,请求obj2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println(Thread.currentThread().getName() + "已经获取到obj1和obj2");
                }
            }
        } else {
            //线程2执行代码
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + "已经获取到资源obj2,请求obj1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + "已经获取到obj1和obj2");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runLock(1);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runLock(2);
            }
        });

        thread1.start();
        thread2.start();
    }
}
