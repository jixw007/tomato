package com.tomato.mycode;

import com.tomato.mycode.config.RouteHolderConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * 动态数据源配置,需要将自有的配置依赖(DynamicDataSourceConfig),将原有的依赖去除(DataSourceAutoConfiguration)
 */
@Import({RouteHolderConfig.class})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@MapperScan({"com.tomato.mycode.mapper"})
public class MainApplication {
    @PostConstruct
    public void printHelloWorld() {
        System.out.print("hello world main !\n");
    }

    @Value("${abm_nats_host_ip:}")
    private String abmNatsHostIp;

    @PostConstruct
    public void printValue() {
        System.out.print("abmNatsHostIp=" + abmNatsHostIp + "\n");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //2020年4月5日 springboot启动
//        SpringApplication.run(MainApplication.class, args);
//        System.out.print("********启动成功****************!\n");

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

        //2020年4月13日 测试用mybatis做数据库连接--测试ok

        //2020年4月20日 测试动态多数据源 --测试ok

        //需要了解具体ChooseDataSource底层具体路由过程

        //@NotNull 和 @Nullable使用

        //测试提交


    }
}
