package com.tomato.mycode.entity;

public class AcctBalanceInfoEntity {
    private Long  lAcctBalanceID;
    private Long  lBalanceTypeId;
    private Long  lBalance;
    private Long  lNewBalance;
    private String sCorpusFlag;

    public Long getlAcctBalanceID() {
        return lAcctBalanceID;
    }

    public void setlAcctBalanceID(Long lAcctBalanceID) {
        this.lAcctBalanceID = lAcctBalanceID;
    }

    public Long getlBalanceTypeId() {
        return lBalanceTypeId;
    }

    public void setlBalanceTypeId(Long lBalanceTypeId) {
        this.lBalanceTypeId = lBalanceTypeId;
    }

    public Long getlBalance() {
        return lBalance;
    }

    public void setlBalance(Long lBalance) {
        this.lBalance = lBalance;
    }

    public Long getlNewBalance() {
        return lNewBalance;
    }

    public void setlNewBalance(Long lNewBalance) {
        this.lNewBalance = lNewBalance;
    }

    public String getsCorpusFlag() {
        return sCorpusFlag;
    }

    public void setsCorpusFlag(String sCorpusFlag) {
        this.sCorpusFlag = sCorpusFlag;
    }
}
