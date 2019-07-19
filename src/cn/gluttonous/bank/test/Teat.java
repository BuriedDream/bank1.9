package cn.gluttonous.bank.test;


import cn.gluttonous.bank.dao.UserDaoInterface;
import cn.gluttonous.bank.dao.impl.MysqlUserDao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/15
 * @Version: 1.0
 **/
public class Teat {
    public static void main(String[] args) {
        UserDaoInterface daoInterface = new MysqlUserDao();
        System.out.println(daoInterface.queryFlag("liam"));
    }
}
