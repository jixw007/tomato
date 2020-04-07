package com.tomato.mycode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class MyTest {
    public interface Predicate<T>{
        boolean Test(T t);
    }

    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> resultApples = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (p.Test(apple)) {
                resultApples.add(apple);
            }
        }
        return resultApples;
    }

    public static void printApples(String number) {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        System.out.print("hello apples :"+ number+"\n");
    }

    @PostConstruct
    public void printHelloWorld(){
        System.out.print("hello world !\n");
    }

    @Value("${abm_nats_host_ip:}")
    private String abmNatsHostIp;

    @PostConstruct
    public void printValue(){
        System.out.print("abmNatsHostIp="+abmNatsHostIp+"\n");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //2020年4月5日 springboot启动
//        SpringApplication.run(MyTest.class, args);

        //2020年4月7日 JSON性能测试
//        ExampleProcess exampleProcess = new ExampleProcess();
//        exampleProcess.jsonPerformanceTest();

        //2020年4月7日 获取类注解信息测试
        ExampleProcess exampleProcess = new ExampleProcess();
        exampleProcess.annotationExampleTest();

//        Thread t1 = new Thread(() -> printApples("one"));
//        Thread t2 = new Thread(() -> printApples("two"));
//        t1.start();
//        t2.start();

//        String name = null;
//        System.out.print("name=\n"+name);
//        Optional name2 = Optional.ofNullagetColorble(name).orElse("hello");
//        System.out.print("name2=\n"+name2);

//        CountDownLatch latch = new CountDownLatch(10);

//        Class cls = Class.forName("Apple");
//        Field[] fields = cls.getDeclaredFields();
//        for(Field f:fields){
//            System.out.print("Field name="+f.getName());
//            System.out.print(", type="+f.getType().toString());
//            System.out.print("\n");
//        }

//        Thread t = new Thread(() -> System.out.print("hello world 1 !"));
//        t.start();
//        System.out.print("hello world 2 !");

//        List<Apple> apples = new ArrayList<Apple>();
//        Apple apple1 = new Apple();
//        apple1.setColor("Green");
//        apple1.setWeight(100);
//        apple1.getWeight();
//        apples.add(apple1);
//
//        Apple apple2 = new Apple();
//        apple2.setColor("Red");
//        apple2.setWeight(160);
//        apples.add(apple2);
//
//        List<Apple> filterAppleResult = filterApples(apples, (Apple a)->"Green".equals(a.getColor()));
//        System.out.print("filterAppleResult="+JSON.toJSONString(filterAppleResult));
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(Apple.class);
//        context.getBean("getWeight");
//        context.getBean("getWeight");
//
//        System.out.print("\n");
//
//        List<Apple> filterAppleResult2 = apples.parallelStream().filter((Apple a)->"Green".equals(a.getColor())).collect(Collectors.toList());
//        System.out.print("filterAppleResult2="+JSON.toJSONString(filterAppleResult2));
    }
}
