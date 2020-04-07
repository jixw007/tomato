package com.tomato.mycode;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExampleProcess {

    //JSON性能测试
    public void jsonPerformanceTest() {
        List<AcctBalanceInfoEntity> acctBalanceInfoEntityList = new ArrayList<AcctBalanceInfoEntity>();
        List<String> acctBalanceInfoEntityJsonList = new ArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            AcctBalanceInfoEntity acctBalanceInfoEntity = new AcctBalanceInfoEntity();
            acctBalanceInfoEntity.setlAcctBalanceID(1111L);
            acctBalanceInfoEntity.setlBalance(2222L);
            acctBalanceInfoEntity.setlBalanceTypeId(3333L);
            acctBalanceInfoEntity.setlNewBalance(4444L);
            acctBalanceInfoEntity.setsCorpusFlag("6666");
            acctBalanceInfoEntityList.add(acctBalanceInfoEntity);

            String stringTemp = JSON.toJSONString(acctBalanceInfoEntity);
            acctBalanceInfoEntityJsonList.add(stringTemp);
        }


        long startTime = System.currentTimeMillis();
        for (AcctBalanceInfoEntity acctBalanceInfoEntity : acctBalanceInfoEntityList) {
            AcctBalanceInfoEntity acctBalanceInfoEntityTemp = new AcctBalanceInfoEntity();
            acctBalanceInfoEntityTemp.setlAcctBalanceID(acctBalanceInfoEntity.getlAcctBalanceID());
            acctBalanceInfoEntityTemp.setlBalance(acctBalanceInfoEntity.getlBalance());
            acctBalanceInfoEntityTemp.setlBalanceTypeId(acctBalanceInfoEntity.getlBalanceTypeId());
            acctBalanceInfoEntityTemp.setlNewBalance(acctBalanceInfoEntity.getlNewBalance());
            acctBalanceInfoEntityTemp.setsCorpusFlag(acctBalanceInfoEntity.getsCorpusFlag());
        }
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.print("acctBalanceInfoEntityList.size()=" + acctBalanceInfoEntityList.size() + ",costTime=" + costTime + "\n");

        long startTime2 = System.currentTimeMillis();
        for (String stringTemp : acctBalanceInfoEntityJsonList) {
            AcctBalanceInfoEntity acctBalanceInfoEntityTemp = new AcctBalanceInfoEntity();
            acctBalanceInfoEntityTemp = JSON.parseObject(stringTemp, AcctBalanceInfoEntity.class);
        }

        long endTime2 = System.currentTimeMillis();
        long costTime2 = endTime2 - startTime2;
        System.out.print("AcctBalanceInfoEntityJsonList.size()=" + acctBalanceInfoEntityJsonList.size() + ",costTime2=" + costTime2);
        //使用json转换性能相差几十倍
    }


    //获取类注解信息测试
    public void annotationExampleTest() throws ClassNotFoundException {
        //注解测试使用
        Class clazz = Class.forName("com.tomato.mycode.Apple");

        //获取类注解信息
        AnnotationExample classAnno = (AnnotationExample) clazz.getAnnotation(AnnotationExample.class);
        System.out.println(classAnno.name() + "---" + classAnno.value() + "---" + classAnno.path());

        //获取所以方法注解信息 ps:这里需要使用 isAnnotationPresent 判断方法上是否使用了注解
        Method[] allMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < allMethods.length; i++) {
            if (allMethods[i].isAnnotationPresent(AnnotationExample.class)) {
                AnnotationExample methodAnno = allMethods[i].getAnnotation(AnnotationExample.class);
                System.out.println("遍历:当前方法名为：" + allMethods[i].getName() + " 的注解信息：---" + methodAnno.name() + "---" + methodAnno.value() + "---" + methodAnno.path());
            }
        }

        //        Class cls = Class.forName("Apple");
//        Field[] fields = cls.getDeclaredFields();
//        for(Field f:fields){
//            System.out.print("Field name="+f.getName());
//            System.out.print(", type="+f.getType().toString());
//            System.out.print("\n");
//        }
    }

    //线程启动使用测试
    public void threadExampleTest() throws ClassNotFoundException {
//        Thread t1 = new Thread(() -> printApples("one"));
//        Thread t2 = new Thread(() -> printApples("two"));
//        t1.start();
//        t2.start();

//        String name = null;
//        System.out.print("name=\n"+name);
//        Optional name2 = Optional.ofNullagetColorble(name).orElse("hello");
//        System.out.print("name2=\n"+name2);

//        CountDownLatch latch = new CountDownLatch(10);

        Thread t = new Thread(() -> System.out.print("hello world one ,thread Id = "+Thread.currentThread().getId()+ "\n"));
        t.start();
        System.out.print("hello world two ,thread Id = "+Thread.currentThread().getId()+  "\n");
    }
}
