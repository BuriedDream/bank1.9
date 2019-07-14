package cn.gluttonous.bank.dao.impl;

import cn.gluttonous.bank.dao.UserDaoInterface;
import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.util.MD5;

import java.io.*;
import java.util.Properties;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/12
 * @Version: 1.0
 **/
public class PropertiesUserDao implements UserDaoInterface {

    /**
     * 创建一个用户
     * 其实就是新建一个文件
     * @param user
     */
    @Override
    public int create(UserBean user) {

        final int SUCCESS = 1;
        final int FAILURE = -1;
        //The file already exists
        final int ALREADY_EXISTS = 0;

        Properties properties = new Properties();
        MD5 md5 = MD5.getInstance();
        File file = new File("src/user/"+md5.getMD5(user.getUserName())+".properties");
        FileOutputStream fileOutputStream = null;

        //用户文件存在
        if(file.exists()){
            return ALREADY_EXISTS;
        }
        else {

            try{
                fileOutputStream = new FileOutputStream(file);

                properties.setProperty("userName",md5.getMD5(user.getUserName()));
                properties.setProperty("password",md5.getMD5(user.getPassword()));
                properties.setProperty("money",String.valueOf(user.getMoneyBean().getMoney()));
                properties.store(fileOutputStream,"user:"+user.getUserName()+"'s account");

            }
            catch (FileNotFoundException e){
                //throw new RuntimeException(e);
                return FAILURE;
            }
            catch (IOException e){
                return FAILURE;
            }
            finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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
     * 更新用户数据
     * @param userName
     * @param moneyBean
     */
    @Override
    public void updateMoney(String userName, MoneyBean moneyBean) {

        Properties properties = new Properties();
        MD5 md5 = MD5.getInstance();
        UserBean user = this.queryUser(userName);
        File file = new File("src/user/"+md5.getMD5(userName)+".properties");
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            properties.setProperty("userName",user.getUserName());
            properties.setProperty("password",user.getPassword());
            properties.setProperty("money",String.valueOf(moneyBean.getMoney()));
            properties.store(fileOutputStream,"user:"+userName+"'s account");
            fileOutputStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 查询 用户
     *
     * @param userName 这是明文
     * @return user 这是密文
     */
    @Override
    public UserBean queryUser(String userName) {

        Properties properties = new Properties();
        MD5 md5 = MD5.getInstance();
        File file = new File("src/user/"+md5.getMD5(userName)+".properties");

        UserBean userBean = new UserBean();

        //用户文件不存在
        if(!file.exists()){
            return null;
        }
        else{
            try{
                FileInputStream fileInputStream = new FileInputStream(file);
                properties.load(fileInputStream);
                fileInputStream.close();

                userBean.setUserName(properties.getProperty("userName"));
                userBean.setPassword(properties.getProperty("password"));
                userBean.setMoney(new MoneyBean(Double.parseDouble(properties.getProperty("money"))));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return userBean;
    }

    /**
     * 查询 用户
     *
     * @param userName 这是MD5密文
     * @return MoneyBean
     */
    @Override
    public MoneyBean queryMoney(String userName) {

        Properties properties = new Properties();
        MD5 md5 = MD5.getInstance();
        File file = new File("src/user/"+md5.getMD5(userName)+".properties");

        MoneyBean moneyBean = new MoneyBean();

        //用户文件不存在
        if(!file.exists()){
            return null;
        }
        else{
            try{
                FileInputStream fileInputStream = new FileInputStream(file);
                properties.load(fileInputStream);
                fileInputStream.close();

                moneyBean.setMoney(Double.parseDouble(properties.getProperty("money")));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return moneyBean;
    }


}
