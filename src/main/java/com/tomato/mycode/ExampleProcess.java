package com.tomato.mycode;

import com.alibaba.fastjson.JSON;

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
}
