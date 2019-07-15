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
    private MoneyBean moneyBean;
    private Timestamp date;
    private MoneyBean afterMoney;

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

    public MoneyBean getMoneyBean() {
        return moneyBean;
    }

    public void setMoneyBean(MoneyBean moneyBean) {
        this.moneyBean = moneyBean;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public MoneyBean getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(MoneyBean afterMoney) {
        this.afterMoney = afterMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        LogBean logBean = (LogBean) o;
        return Objects.equals(userName, logBean.userName) &&
                Objects.equals(log, logBean.log) &&
                Objects.equals(moneyBean, logBean.moneyBean) &&
                Objects.equals(date, logBean.date) &&
                Objects.equals(afterMoney, logBean.afterMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, log, moneyBean, date, afterMoney);
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "userName='" + userName + '\'' +
                ", log='" + log + '\'' +
                ", moneyBean=" + moneyBean +
                ", date=" + date +
                ", afterMoney=" + afterMoney +
                '}';
    }
}
