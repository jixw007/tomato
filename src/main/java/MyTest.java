import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void main(String[] args){

        List<AcctBalanceInfoEntity> acctBalanceInfoEntityList = new ArrayList<AcctBalanceInfoEntity>();
        List<String> stringList = new ArrayList<String>();
        for(int i=0; i <10000; i++){
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
        System.out.print("costTime="+costTime+"\n");

        long startTime2 = System.currentTimeMillis();
        for(String stringTemp: stringList){
            AcctBalanceInfoEntity acctBalanceInfoEntityTemp = new AcctBalanceInfoEntity();
            acctBalanceInfoEntityTemp = JSON.parseObject(stringTemp, AcctBalanceInfoEntity.class);

        }
        long endTime2 = System.currentTimeMillis();
        long costTime2 = endTime2 - startTime2;
        System.out.print("costTime2="+costTime2);
    }
}
