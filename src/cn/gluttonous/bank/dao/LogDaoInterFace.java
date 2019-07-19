package cn.gluttonous.bank.dao;

import cn.gluttonous.bank.dao.tool.LogPageBean;
import cn.gluttonous.bank.model.LogBean;
import cn.gluttonous.bank.model.MoneyBean;

import java.util.Date;
import java.util.List;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/15
 * @Version: 1.0
 **/
public interface LogDaoInterFace {
    /**
     * 插入一条log
     * @param logBean
     */
    public void insert(LogBean logBean);

    /**
     * 依据 userName 查询log
     * @param logPageBean
     * @return
     */
    public void query(LogPageBean<LogBean> logPageBean, String userName);

    /**
     * 返回总记录数
     * @param UserName
     * @return
     */
    public int getTotalCount(String UserName);
}
