package cn.gluttonous.bank.view.test;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/16
 * @Version: 1.0
 **/
public class User {

    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
