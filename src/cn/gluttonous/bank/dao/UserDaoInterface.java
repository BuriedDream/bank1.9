package cn.gluttonous.bank.dao;

import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.model.UserBean;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/12
 * @Version: 1.0
 **/
public interface UserDaoInterface {

    /**
     * 创建一个用户
     * @param user
     * @return SUCCESS = 1
     *      *         FAILURE = -1
     *      *         //The file already exists
     *      *        ALREADY_EXISTS = 0
     */
    public int create(UserBean user);

    /**
     * 删除一个用户
     * @param user
     */
    public void delete(UserBean user);

    /**
     * 更新用户 Money数据
     * @param userName
     * @param moneyBean
     */
    public void updateMoney(String userName, MoneyBean moneyBean);

    /**
     * 查询 用户
     * @param userName
     * @return user
     */
    public UserBean queryUser(String userName);

    /**
     * 查询 用户
     * @param userName
     * @return MoneyBean
     */
    public MoneyBean queryMoney(String userName);

    /**
     * 查询用户的状态
     * @param userName
     * @return
     */
    public Boolean queryFlag(String userName);
}

