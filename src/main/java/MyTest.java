import com.alibaba.fastjson.JSON;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
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

    public static void main(String[] args) throws ClassNotFoundException {
//        Thread t1 = new Thread(() -> printApples("one"));
//        Thread t2 = new Thread(() -> printApples("two"));
//        t1.start();
//        t2.start();

//        String name = null;
//        System.out.print("name=\n"+name);
//        Optional name2 = Optional.ofNullable(name).orElse("hello");
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
//        System.out.print("\n");
//
//        List<Apple> filterAppleResult2 = apples.parallelStream().filter((Apple a)->"Green".equals(a.getColor())).collect(Collectors.toList());
//        System.out.print("filterAppleResult2="+JSON.toJSONString(filterAppleResult2));



 //json转化性能测试
//        List<AcctBalanceInfoEntity> acctBalanceInfoEntityList = new ArrayList<AcctBalanceInfoEntity>();
//        List<String> stringList = new ArrayList<String>();
//        for(int i=0; i <100000; i++){
//            AcctBalanceInfoEntity acctBalanceInfoEntity = new AcctBalanceInfoEntity();
//            acctBalanceInfoEntity.setlAcctBalanceID(1111L);
//            acctBalanceInfoEntity.setlBalance(2222L);
//            acctBalanceInfoEntity.setlBalanceTypeId(3333L);
//            acctBalanceInfoEntity.setlNewBalance(4444L);
//            acctBalanceInfoEntity.setsCorpusFlag("6666");
//            acctBalanceInfoEntityList.add(acctBalanceInfoEntity);
//
//            String stringTemp = JSON.toJSONString(acctBalanceInfoEntity);
//            stringList.add(stringTemp);
//        }
//
//
//        long startTime = System.currentTimeMillis();
//        for(AcctBalanceInfoEntity acctBalanceInfoEntity: acctBalanceInfoEntityList){
//            AcctBalanceInfoEntity acctBalanceInfoEntityTemp = new AcctBalanceInfoEntity();
//            acctBalanceInfoEntityTemp.setlAcctBalanceID(acctBalanceInfoEntity.getlAcctBalanceID());
//            acctBalanceInfoEntityTemp.setlBalance(acctBalanceInfoEntity.getlBalance());
//            acctBalanceInfoEntityTemp.setlBalanceTypeId(acctBalanceInfoEntity.getlBalanceTypeId());
//            acctBalanceInfoEntityTemp.setlNewBalance(acctBalanceInfoEntity.getlNewBalance());
//            acctBalanceInfoEntityTemp.setsCorpusFlag(acctBalanceInfoEntity.getsCorpusFlag());
//
//        }
//        long endTime = System.currentTimeMillis();
//        long costTime = endTime - startTime;
//        System.out.print("acctBalanceInfoEntityList.size()="+acctBalanceInfoEntityList.size()+",costTime="+costTime+"\n");
//
//        long startTime2 = System.currentTimeMillis();
//        for(String stringTemp: stringList){
//            AcctBalanceInfoEntity acctBalanceInfoEntityTemp = new AcctBalanceInfoEntity();
//            acctBalanceInfoEntityTemp = JSON.parseObject(stringTemp, AcctBalanceInfoEntity.class);
//        }
//
//        long endTime2 = System.currentTimeMillis();
//        long costTime2 = endTime2 - startTime2;
//        System.out.print("stringList.size()="+stringList.size()+",costTime2="+costTime2);
    }
}
