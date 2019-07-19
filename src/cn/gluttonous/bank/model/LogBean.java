package cn.gluttonous.bank.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/15
 * @Version: 1.0
 **/
public class LogBean {
    private String userName;
    private String log;
    private double money;
    private Timestamp logDate;
    private double afterMoney;

    public LogBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }

    public double getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(double afterMoney) {
        this.afterMoney = afterMoney;
    }
}
