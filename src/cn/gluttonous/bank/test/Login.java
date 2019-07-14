package cn.gluttonous.bank.test;

import cn.gluttonous.bank.manager.Manager;
import cn.gluttonous.bank.manager.impl.ManagerImpl;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/13
 * @Version: 1.0
 **/
public class Login extends LoginView {

    /**
     * 对{loginButton}进行监听
     *
     * @param loginButton
     */
    @Override
    public void login(JButton loginButton) {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                String password = new String(passwordText.getPassword());
//checkValidCode()
                if(true){

                    if(checkUserNameAndPassword()){

                        if(ManagerImpl.getInstance(new UserBean(userName,password)).login()){

                            new Home(new UserBean(userName,password));
                            dispose();
                        }else {
                            loginFail();
                        }
                    }
                }

                userText.setText(userName);
                passwordText.setText(password);

            }
        });
    }

    /**
     * 对{cancelButton}进行监听
     *
     * @param cancelButton
     */
    @Override
    public void cancel(JButton cancelButton) {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Welcome();
                dispose();
            }
        });
    }
}
