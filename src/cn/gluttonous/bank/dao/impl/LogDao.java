package cn.gluttonous.bank.dao.impl;

import cn.gluttonous.bank.dao.LogDaoInterFace;
import cn.gluttonous.bank.dao.tool.LogPageBean;
import cn.gluttonous.bank.model.LogBean;
import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.util.JdbcUtil;
import cn.gluttonous.bank.util.Jdbc_c3p0_util;
import cn.gluttonous.bank.util.MD5;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

        try {
            String sql_insert = "INSERT INTO userlog(userName,money,LOG,logDate,afterMoney) VALUES (?,?,?,?,?)";

            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();
            queryRunner.update(sql_insert,md5.getMD5(logBean.getUserName()), logBean.getMoney(),logBean.getLog(),logBean.getLogDate(),logBean.getAfterMoney());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 依据 userName 查询log
     *
     * @param logPageBean
     * @return
     */
    @Override
    public void query(LogPageBean<LogBean> logPageBean, String userName) {

        //总记录数
        logPageBean.setTotalCount(this.getTotalCount(userName));

        //设置当前页
        if(logPageBean.getCurrentPage() <= 0){
            logPageBean.setCurrentPage(1);
        }else if(logPageBean.getCurrentPage() > logPageBean.getTotalCount()){
            logPageBean.setCurrentPage(logPageBean.getTotalPage());
        }

        //获取当前页： 计算查询的起始行、返回的行数
        int currentPage = logPageBean.getCurrentPage();
        // 查询的起始行
        int index = (currentPage -1 ) * logPageBean.getPageCount();
        // 查询返回的行数
        int count = logPageBean.getPageCount();


        try {
            String sql_select = "SELECT * FROM usertable WHERE userName = ? LiMIT ?,?";
            MD5 md5 = MD5.getInstance();
            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();
            List<LogBean> logBeans = queryRunner.query(sql_select,new BeanListHandler<LogBean>(LogBean.class),md5.getMD5(userName),index,count);

            logPageBean.setPageData(logBeans);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 返回总记录数
     * @param UserName
     * @return
     */
    @Override
    public int getTotalCount(String UserName) {
        String sql = "SELECT COUNT(*) FROM userlog WHERE username=?";
        MD5 md5 = MD5.getInstance();
        try {
            QueryRunner queryRunner = Jdbc_c3p0_util.getQueryRuner();
            Integer count = queryRunner.query(sql,new ScalarHandler<Integer>(),md5.getMD5(UserName));
            return count;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
