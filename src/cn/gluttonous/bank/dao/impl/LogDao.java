package cn.gluttonous.bank.dao.impl;

import cn.gluttonous.bank.dao.LogDaoInterFace;
import cn.gluttonous.bank.model.LogBean;
import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.util.JdbcUtil;
import cn.gluttonous.bank.util.MD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/15
 * @Version: 1.0
 **/
public class LogDao implements LogDaoInterFace {


    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * 插入一条log
     *
     * @param logBean
     */
    @Override
    public void insert(LogBean logBean) {
        MD5 md5 = MD5.getInstance();

        connection = JdbcUtil.getConnection();

        try {

            String sql_insert = "INSERT INTO userlog(username,money,LOG,logDate,afterMoney) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql_insert);
            preparedStatement.setString(1,md5.getMD5(logBean.getUserName()));
            preparedStatement.setDouble(2,logBean.getMoneyBean().getMoney());
            preparedStatement.setString(3,logBean.getLog());
            preparedStatement.setString(4,logBean.getDate().toString());
            preparedStatement.setDouble(5,logBean.getAfterMoney().getMoney());
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
     * 依据 userName 查询log
     *
     * @param userName
     * @return
     */
    @Override
    public List<LogBean> query(String userName) {

        MD5 md5 = MD5.getInstance();

        connection = JdbcUtil.getConnection();

        try {
             /*
             long longTime = date.getTime();
		     Timestamp timestamp = new Timestamp(longTime);
		     System.out.println(timestamp); // 2017-09-24 23:33:20.655
             */

            String sql_select = "SELECT * FROM usertable WHERE userName = ?";
            preparedStatement = connection.prepareStatement(sql_select);
            preparedStatement.setString(1,md5.getMD5(userName));
            resultSet = preparedStatement.executeQuery();
            List<LogBean> logBeans = new ArrayList<>();
            while (resultSet.next()){
                LogBean logBean = new LogBean();
                logBean.setUserName(resultSet.getString("userName"));
                logBean.setMoneyBean(new MoneyBean(resultSet.getDouble("money")));
                logBean.setLog(resultSet.getString("log"));
                logBean.setAfterMoney(new MoneyBean(resultSet.getDouble("afterMoney")));
                logBean.setDate(resultSet.getTimestamp("logDate"));
                logBeans.add(logBean);
            }
            return logBeans;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtil.closeAll(connection,preparedStatement,resultSet);
        }
    }
}
