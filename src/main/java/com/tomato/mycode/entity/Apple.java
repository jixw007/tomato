package com.tomato.mycode.entity;

import java.util.ArrayList;
import java.util.List;

public class Apple {
    private Long appleId;
    private String color;
    private Integer weight;

    public Long getAppleId() {
        return appleId;
    }

    public void setAppleId(Long appleId) {
        this.appleId = appleId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return "Green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    //颜色绿色
    public static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> greenApples = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if ("Green".equals(apple.getColor())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    //重量大于150
    public static List<Apple> filterHeavyApples(List<Apple> apples) {
        List<Apple> heavyApples = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (apple.getWeight() > 150) {
                heavyApples.add(apple);
            }
        }
        return heavyApples;
    }
}
