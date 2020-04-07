package com.tomato.mycode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class MyTest {
    @PostConstruct
    public void printHelloWorld(){
        System.out.print("hello world main !\n");
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
//        ExampleProcess exampleProcess = new ExampleProcess();
//        exampleProcess.annotationExampleTest();

        //2020年4月7日 获线程启动使用测试
//        ExampleProcess exampleProcess = new ExampleProcess();
//        exampleProcess.threadExampleTest();

        //2020年4月7日 苹果filterApples使用测试
        ExampleProcess exampleProcess = new ExampleProcess();
        exampleProcess.appleExampleTest1();

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
