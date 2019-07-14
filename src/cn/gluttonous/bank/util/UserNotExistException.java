package cn.gluttonous.bank.util;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/14
 * @Version: 1.0
 **/
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
    }

    public UserNotExistException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "用户不存在！";
    }
}
