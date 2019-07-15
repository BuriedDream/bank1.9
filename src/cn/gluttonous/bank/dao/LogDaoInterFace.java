package cn.gluttonous.bank.dao;

import cn.gluttonous.bank.model.MoneyBean;

import java.util.Date;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/15
 * @Version: 1.0
 **/
public interface LogDaoInterFace {
    public void insert(String userName, MoneyBean moneyBean, String log, Date date);

}
