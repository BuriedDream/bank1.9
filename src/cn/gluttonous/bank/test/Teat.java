package cn.gluttonous.bank.test;


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

        //获得系统时间.
        Date date = new Date();
        //将时间格式转换成符合Timestamp要求的格式
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
         //把时间转换
        Timestamp goodsC_date =Timestamp.valueOf(nowTime);
        System.out.println(goodsC_date);
    }
}
