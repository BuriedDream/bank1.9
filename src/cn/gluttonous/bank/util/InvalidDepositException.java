package cn.gluttonous.bank.util;

/**
 * @title: bank
 * @Description:
 *     存款为负数异常
 * @Author: xfm
 * @Date: 2019/6/16
 * @Version: 1.0
 **/
public class InvalidDepositException extends RuntimeException {

    public InvalidDepositException() {
    }

    public InvalidDepositException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "金额不能为负！";
    }
}
