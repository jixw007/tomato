package com.tomato.mycode.tomatotest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        int value = 2 << 3;
        System.out.println("value=" + value);
        List<String> strs = new ArrayList<>();
        strs.add("abc");
        strs.add("abcd");
        //filter过滤
        Stream<String> stream = strs.stream().filter(x -> x.equals("abc"));
        List<String> strs2 = stream.collect(Collectors.toList());//将Stream转化为List
        System.out.println("strs2=" + strs2);
    }
}
