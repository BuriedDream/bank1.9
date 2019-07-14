package cn.gluttonous.bank.util;

/**
 * @title: bank
 * @Description:a)
 *  	AccountOverDrawnException
 *      作用：取款超出余额时抛出异常
 * @Author: xfm
 * @Date: 2019/6/16
 * @Version: 1.0
 **/
public class AccountOverDrawnException extends RuntimeException {

    public AccountOverDrawnException() {
    }

    public AccountOverDrawnException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "余额不足！";
    }
}
