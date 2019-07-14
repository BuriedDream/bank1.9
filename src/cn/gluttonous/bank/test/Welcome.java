package cn.gluttonous.bank.test;

import cn.gluttonous.bank.view.WelcomeView;

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
public class Welcome extends WelcomeView {
    /**
     * 对{loginButton}进行监听
     *
     * @param loginButton
     */
    @Override
    protected void login(JButton loginButton) {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });

    }

    /**
     * 对{loginButton}进行监听
     *
     * @param registrationButton
     */
    @Override
    public void register(JButton registrationButton) {
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registered();
                dispose();
            }
        });

    }

    /**
     * 对{cancelButton}进行监听
     *
     * @param cancelButton
     */
    @Override
    public void exit(JButton cancelButton) {

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
