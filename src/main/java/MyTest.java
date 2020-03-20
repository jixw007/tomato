import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

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

    //JSON性能测试
     public static void jsonPerformanceTest(){
        List<AcctBalanceInfoEntity> acctBalanceInfoEntityList = new ArrayList<AcctBalanceInfoEntity>();
        List<String> stringList = new ArrayList<String>();
        for(int i=0; i <100000; i++){
            AcctBalanceInfoEntity acctBalanceInfoEntity = new AcctBalanceInfoEntity();
            acctBalanceInfoEntity.setlAcctBalanceID(1111L);
            acctBalanceInfoEntity.setlBalance(2222L);
            acctBalanceInfoEntity.setlBalanceTypeId(3333L);
            acctBalanceInfoEntity.setlNewBalance(4444L);
            acctBalanceInfoEntity.setsCorpusFlag("6666");
            acctBalanceInfoEntityList.add(acctBalanceInfoEntity);

            String stringTemp = JSON.toJSONString(acctBalanceInfoEntity);
            stringList.add(stringTemp);
        }


        long startTime = System.currentTimeMillis();
        for(AcctBalanceInfoEntity acctBalanceInfoEntity: acctBalanceInfoEntityList){
            AcctBalanceInfoEntity acctBalanceInfoEntityTemp = new AcctBalanceInfoEntity();
            acctBalanceInfoEntityTemp.setlAcctBalanceID(acctBalanceInfoEntity.getlAcctBalanceID());
            acctBalanceInfoEntityTemp.setlBalance(acctBalanceInfoEntity.getlBalance());
            acctBalanceInfoEntityTemp.setlBalanceTypeId(acctBalanceInfoEntity.getlBalanceTypeId());
            acctBalanceInfoEntityTemp.setlNewBalance(acctBalanceInfoEntity.getlNewBalance());
            acctBalanceInfoEntityTemp.setsCorpusFlag(acctBalanceInfoEntity.getsCorpusFlag());
        }
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.print("acctBalanceInfoEntityList.size()="+acctBalanceInfoEntityList.size()+",costTime="+costTime+"\n");

        long startTime2 = System.currentTimeMillis();
        for(String stringTemp: stringList){
            AcctBalanceInfoEntity acctBalanceInfoEntityTemp = new AcctBalanceInfoEntity();
            acctBalanceInfoEntityTemp = JSON.parseObject(stringTemp, AcctBalanceInfoEntity.class);
        }

        long endTime2 = System.currentTimeMillis();
        long costTime2 = endTime2 - startTime2;
        System.out.print("stringList.size()="+stringList.size()+",costTime2="+costTime2);
    }

    public static void main(String[] args) throws ClassNotFoundException {

        //JSON性能测试
        jsonPerformanceTest();

        //注解测试使用
//        Class clazz = Class.forName("Apple");
//
//        //获取类注解信息
//        AnnotationExample classAnno =(AnnotationExample) clazz.getAnnotation(AnnotationExample.class);
//        System.out.println( classAnno.name()+"---"+classAnno.value()+"---"+classAnno.path());
//
//        //获取所以方法注解信息 ps:这里需要使用 isAnnotationPresent 判断方法上是否使用了注解
//        Method[] allMethods = clazz.getDeclaredMethods();
//        for(int i=0;i<allMethods.length;i++){
//            if(allMethods[i].isAnnotationPresent(AnnotationExample.class)) {
//                AnnotationExample methodAnno = allMethods[i].getAnnotation(AnnotationExample.class);
//                System.out.println("遍历:当前方法名为："+allMethods[i].getName()+" 的注解信息：---"+methodAnno.name() + "---" + methodAnno.value() + "---" + methodAnno.path());
//            }
//        }


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
