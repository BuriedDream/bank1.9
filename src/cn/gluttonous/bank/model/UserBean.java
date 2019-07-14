package cn.gluttonous.bank.model;

import java.util.Objects;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/14
 * @Version: 1.0
 **/
public class UserBean {

    private String userName;
    private String password;
    private MoneyBean moneyBean;

    public UserBean(String userName, String password, MoneyBean money) {
        this.userName = userName;
        this.password = password;
        this.moneyBean = money;
    }

    public UserBean() {
    }

    public UserBean(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.moneyBean = new MoneyBean(0);
    }

    public UserBean(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MoneyBean getMoneyBean() {
        return moneyBean;
    }

    public void setMoney(MoneyBean money) {
        this.moneyBean = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        UserBean userBean = (UserBean) o;
        return Objects.equals(userName, userBean.userName) &&
                Objects.equals(password, userBean.password) &&
                Objects.equals(moneyBean, userBean.moneyBean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, moneyBean);
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", money=" + moneyBean +
                '}';
    }
}
