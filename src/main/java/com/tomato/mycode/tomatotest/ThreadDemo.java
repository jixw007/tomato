package com.tomato.mycode.tomatotest;

import com.tomato.mycode.entity.Apple;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Apple> AppleList= new ArrayList<>();
        for(int i=0; i<10000000; i++){
            AppleList.add(new Apple());
            Thread.sleep(100);
            System.out.println("i="+i);
        }
    }
}
