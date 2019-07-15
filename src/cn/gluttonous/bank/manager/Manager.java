package cn.gluttonous.bank.manager;

import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.util.AccountOverDrawnException;
import cn.gluttonous.bank.util.InvalidDepositException;
import cn.gluttonous.bank.util.UserNotExistException;

/**
 * @title: bank
 * @Description:
 * @Author: xfm
 * @Date: 2019/6/5
 * @Version: 0.0
 **/
public interface Manager {

    /**
     * 存款
     * @param money
     * @throws InvalidDepositException
     */
    public void deposit(double money) throws InvalidDepositException;

    /**
     * 取款
     * @param money
     * @throws AccountOverDrawnException
     */
    public void withdrawals(double money) throws AccountOverDrawnException;

    /**
     * 查询余额
     * @return
     */
    public MoneyBean inquiry();

    /**
     *
     */
    public void exitSystem();

    /**
     * 转账
     * @param name 转向用户名
     * @param moneyBean
     */
    public void transfer(String name, MoneyBean moneyBean)throws InvalidDepositException, UserNotExistException;

    /**
     * 注册
     * @return SUCCESS = 1
     *         FAILURE = -1
     *        ALREADY_EXISTS = 0   //The file already exists
     */
    public int register(UserBean user);

    /**
     * 登录
     * @return boolean 是否成功
     */
    public boolean login();

}