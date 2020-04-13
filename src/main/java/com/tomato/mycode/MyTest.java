package com.tomato.mycode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
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
        SpringApplication.run(MyTest.class, args);
        System.out.print("********启动成功****************!\n");

//        for (int i = 0; i < 10000000; i++) {
//            Apple Apple = new Apple();
//            System.out.print("this is number {"+i+"}\n");
//        }

        //2020年4月7日 JSON性能测试
//        ExampleProcess exampleProcess = new ExampleProcess();
//        exampleProcess.jsonPerformanceTest();

        //2020年4月7日 获取类注解信息测试
//        ExampleProcess exampleProcess = new ExampleProcess();
//        exampleProcess.annotationExampleTest();

        //2020年4月7日 获线程启动使用测试
//        ExampleProcess exampleProcess = new ExampleProcess();
//        exampleProcess.threadExampleTest();

        //2020年4月7日 苹果filterApples使用测试：通过模版、lamdba
//        ExampleProcess exampleProcess = new ExampleProcess();
//        exampleProcess.appleExampleTest1();

//        ApplicationContext context = new AnnotationConfigApplicationContext(Apple.class);
//        context.getBean("getWeight");
//        context.getBean("getWeight");

        //2020年4月10日 测试AOP面向切面编程
        //可以用来配置事务、做日志、权限验证、在用户请求时做一些处理
        //HelloController通过springboot容器启动测试

        //测试数据库连接

        //测试动态多数据源
    }
}
