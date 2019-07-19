package cn.gluttonous.bank.dao.impl;

import cn.gluttonous.bank.dao.UserDaoInterface;
import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.util.JdbcUtil;
import cn.gluttonous.bank.util.Jdbc_c3p0_util;
import cn.gluttonous.bank.util.MD5;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/19
 * @Version: 1.0
 **/
public class UserDao_c3p0 implements UserDaoInterface {


    /**
     * 创建一个用户
     *
     * @param user
     * @return SUCCESS = 1
     * *         FAILURE = -1
     * *         //The file already exists
     * *        ALREADY_EXISTS = 0
     */
    @Override
    public int create(UserBean user) {

        final int SUCCESS = 1;
        final int FAILURE = -1;
        //The file already exists
        final int ALREADY_EXISTS = 0;

        MD5 md5 = MD5.getInstance();


        try {
            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();

            UserBean userBean = queryUser(user.getUserName());
            if(userBean != null) {
                return ALREADY_EXISTS;
            }

            String sql_insert = "INSERT INTO usertable(userName,PASSWORD,money) VALUES (?,?,?)";
            queryRunner.update(md5.getMD5(user.getUserName()),md5.getMD5(user.getPassword()),user.getMoneyBean().getMoney());

        }
        catch (SQLException e) {
            //throw new RuntimeException(e);
            return FAILURE;
        }
        return SUCCESS;
    }

    /**
     * 删除一个用户
     *
     * @param user
     */
    @Override
    public void delete(UserBean user) {

    }

    /**
     * 更新用户 Money数据
     *
     * @param userName
     * @param moneyBean
     */
    @Override
    public void updateMoney(String userName, MoneyBean moneyBean) {

        MD5 md5 = MD5.getInstance();
        try {

            String sql_update = "UPDATE usertable SET money=? WHERE userName=?";

            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();
            queryRunner.update(sql_update,moneyBean.getMoney(),md5.getMD5(userName));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询 用户
     *
     * @param userName
     * @return user
     */
    @Override
    public UserBean queryUser(String userName) {

        MD5 md5 = MD5.getInstance();

        try {
            String sql_select = "SELECT * FROM usertable WHERE userName = ?";
            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();
            UserBean userBean = queryRunner.query(sql_select,new BeanHandler<UserBean>(UserBean.class),md5.getMD5(userName));

            return userBean;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询 用户
     *
     * @param userName
     * @return MoneyBean
     */
    @Override
    public MoneyBean queryMoney(String userName) {
        MD5 md5 = MD5.getInstance();

        try {

            String sql_select = "SELECT money FROM usertable WHERE userName = ?";
            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();
            MoneyBean moneyBean = new MoneyBean(queryRunner.query(sql_select,new ScalarHandler<Double>(),md5.getMD5(userName)));

            return moneyBean;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询用户的状态
     * @param userName
     * @return
     */
    @Override
    public Boolean queryFlag(String userName){
        MD5 md5 = MD5.getInstance();

        Boolean flag = false;
        try {
            String sql_select = "SELECT * FROM usertable WHERE userName = ?";
            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();
            flag = queryRunner.query(sql_select,new ScalarHandler<Boolean>(),md5.getMD5(userName));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }
}
