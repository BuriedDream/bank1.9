package cn.gluttonous.bank.dao.impl;

import cn.gluttonous.bank.dao.UserDaoInterface;
import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.util.JdbcUtil;
import cn.gluttonous.bank.util.MD5;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/15
 * @Version: 1.0
 **/
public class MysqlUserDao implements UserDaoInterface {
    /**
     * 创建一个用户
     *
     * @param user
     * @return SUCCESS = 1
     * *         FAILURE = -1
     * *         //The file already exists
     * *        ALREADY_EXISTS = 0
     */

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public int create(UserBean user) {

        final int SUCCESS = 1;
        final int FAILURE = -1;
        //The file already exists
        final int ALREADY_EXISTS = 0;

        MD5 md5 = MD5.getInstance();

        connection = JdbcUtil.getConnection();

        try {
            String sql_select = "SELECT * FROM usertable WHERE userName = ?";
            preparedStatement = connection.prepareStatement(sql_select);
            preparedStatement.setString(1,md5.getMD5(user.getUserName()));
            resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                return ALREADY_EXISTS;
            }

            String sql_insert = "INSERT INTO usertable(userName,PASSWORD,money) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(sql_insert);
            preparedStatement.setString(1,md5.getMD5(user.getUserName()));
            preparedStatement.setString(2,md5.getMD5(user.getPassword()));
            preparedStatement.setDouble(3,user.getMoneyBean().getMoney());
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            //throw new RuntimeException(e);
            return FAILURE;
        }
        finally {
            JdbcUtil.closeAll(connection,preparedStatement,resultSet);
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

        connection = JdbcUtil.getConnection();

        try {

            String sql_update = "UPDATE usertable SET money=? WHERE userName=?";
            preparedStatement = connection.prepareStatement(sql_update);
            preparedStatement.setDouble(1,moneyBean.getMoney());
            preparedStatement.setString(2,md5.getMD5(userName));

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtil.closeAll(connection,preparedStatement,resultSet);
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

        connection = JdbcUtil.getConnection();

        try {
            String sql_select = "SELECT * FROM usertable WHERE userName = ?";
            preparedStatement = connection.prepareStatement(sql_select);
            preparedStatement.setString(1,md5.getMD5(userName));
            resultSet =preparedStatement.executeQuery();

            if(resultSet.next()){
                UserBean userBean = new UserBean();
                userBean.setUserName(resultSet.getString("userName"));
                userBean.setPassword(resultSet.getString("password"));
                userBean.setMoney(new MoneyBean(resultSet.getDouble("money")));
                return userBean;
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtil.closeAll(connection,preparedStatement,resultSet);
        }

        return null;
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

        connection = JdbcUtil.getConnection();

        try {
            String sql_select = "SELECT money FROM usertable WHERE userName = ?";
            preparedStatement = connection.prepareStatement(sql_select);
            preparedStatement.setString(1,md5.getMD5(userName));
            resultSet =preparedStatement.executeQuery();

            if(resultSet.next()){
                return new MoneyBean(resultSet.getDouble("money"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtil.closeAll(connection,preparedStatement,resultSet);
        }

        return null;
    }

    /**
     * 查询用户的状态
     * @param userName
     * @return
     */
    @Override
    public Boolean queryFlag(String userName){
        MD5 md5 = MD5.getInstance();
        connection = JdbcUtil.getConnection();

        Boolean flag = false;
        try {
            String sql_select = "SELECT * FROM usertable WHERE userName = ?";
            preparedStatement = connection.prepareStatement(sql_select);
            preparedStatement.setString(1,md5.getMD5(userName));
            resultSet =preparedStatement.executeQuery();

            if(resultSet.next()){
                flag = resultSet.getBoolean("state");
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtil.closeAll(connection,preparedStatement,resultSet);
        }

        return flag;
    }
}
