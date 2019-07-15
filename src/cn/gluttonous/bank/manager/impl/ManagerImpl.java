package cn.gluttonous.bank.manager.impl;

import cn.gluttonous.bank.dao.UserDaoInterface;
import cn.gluttonous.bank.factory.UserDaoFactory;
import cn.gluttonous.bank.manager.Manager;
import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.util.AccountOverDrawnException;
import cn.gluttonous.bank.util.InvalidDepositException;
import cn.gluttonous.bank.util.MD5;
import cn.gluttonous.bank.util.UserNotExistException;

import java.io.*;
import java.util.Properties;


/**
 * @title: bank
 * @Description:
 * @Author: xfm
 * @Date: 2019/6/4
 * @Version: 1.7
 **/
public class ManagerImpl implements Manager {

    private UserBean userBean = null;

    public static ManagerImpl manager = null;
    public static synchronized ManagerImpl getInstance(UserBean userBean){
        if (manager == null){
            manager = new ManagerImpl(userBean);
        }

        return manager;
    }

    private ManagerImpl(UserBean userBean) {
        this.userBean = userBean;
    }

    /**
     * 业务：存钱
     * 逻辑：1.判断money是否合理（大于等于零）
     * @param money
     */
    @Override
    public void deposit(double money) throws InvalidDepositException {


        if(money<0){
            throw new InvalidDepositException(money+" 不合法！");
        }
        else {
            userBean.getMoneyBean().setMoney(userBean.getMoneyBean().getMoney() + money);
        }
    }

    /**
     * 业务：取钱
     * 逻辑：1.判断余额是否大于money  大于则取钱成功   小于则报余额不足
     * @param money
     */
    @Override
    public void withdrawals(double money) throws AccountOverDrawnException {

        if(money > userBean.getMoneyBean().getMoney()){
            throw new AccountOverDrawnException();
        }
        else {
            userBean.getMoneyBean().setMoney(userBean.getMoneyBean().getMoney()-money);
        }
    }

    /**
     * 显示余额
     * @return
     */
    @Override
    public MoneyBean inquiry() {
        return userBean.getMoneyBean();
    }

    @Override
    public void exitSystem() {
        UserDaoInterface bankDao = UserDaoFactory.getInstance().getDao();
        bankDao.updateMoney(userBean.getUserName(),userBean.getMoneyBean());
        System.exit(0);
        //System.out.println("系统退出成功！");
    }

    /**
     * 功能：转账
     */
    @Override
    public void transfer(String name, MoneyBean moneyBean)throws InvalidDepositException,UserNotExistException {

        UserDaoInterface dao = UserDaoFactory.getInstance().getDao();
        UserBean user = dao.queryUser(name);
        if(user == null){
            throw new UserNotExistException();
        }
        user.setMoney(new MoneyBean(moneyBean.getMoney()+user.getMoneyBean().getMoney()));
        //？ 这里传的是密文  ，但要接受明文
        dao.updateMoney(name,user.getMoneyBean());
        if(userBean.getMoneyBean().getMoney() - user.getMoneyBean().getMoney() < 0){
            throw new AccountOverDrawnException();
        }
        userBean.setMoney(new MoneyBean(userBean.getMoneyBean().getMoney() - user.getMoneyBean().getMoney()));
    }

    /**
     * 功能：注册
     */
    @Override
    public int register(UserBean user) {
        UserDaoInterface userDao = UserDaoFactory.getInstance().getDao();
        return userDao.create(user);
    }

    /**
     * 功能：登录
     */
    @Override
    public boolean login() {

        UserDaoInterface userDao = UserDaoFactory.getInstance().getDao();

        String userName = userBean.getUserName();
        String password = userBean.getPassword();

        UserBean user = userDao.queryUser(userName);

        MD5 md5 = MD5.getInstance();
        if(user.getUserName().equals(md5.getMD5(userName)) && user.getPassword().equals(md5.getMD5(password))){
            this.userBean.setMoney(user.getMoneyBean());
            return true;
        }
        else {
            return false;
        }
    }


}
