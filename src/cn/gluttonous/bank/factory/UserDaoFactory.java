package cn.gluttonous.bank.factory;

import cn.gluttonous.bank.dao.UserDaoInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @title: bank
 * @Description:
 * @Author: xfm
 * @Date: 2019/6/30
 * @Version: 1.6
 **/
public class UserDaoFactory {

    public static UserDaoFactory userDaoFactory = null;

    public static synchronized UserDaoFactory getInstance(){
        if(userDaoFactory == null){
            userDaoFactory = new UserDaoFactory();
        }
        return userDaoFactory;
    }

    private String daoName;
    private UserDaoFactory(){
        Properties properties = new Properties();
        File file = new File("src/classInfo.properties");
        FileInputStream fileInputStream;
        try{
            fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch(
                IOException e) {
            e.printStackTrace();
        }
        this.daoName = properties.getProperty("className");
    }

    public UserDaoInterface getDao(){
        UserDaoInterface dao = null;
        try {
            Class daoClass = Class.forName(this.daoName);
            dao = (UserDaoInterface) daoClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return dao;
    }
}
