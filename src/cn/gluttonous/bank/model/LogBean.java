package cn.gluttonous.bank.model;

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
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "userName='" + userName + '\'' +
                ", log='" + log + '\'' +
                ", moneyBean=" + moneyBean +
                ", date=" + date +
                '}';
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
                Objects.equals(date, logBean.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, log, moneyBean, date);
    }
}
