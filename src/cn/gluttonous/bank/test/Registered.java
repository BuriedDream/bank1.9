package cn.gluttonous.bank.test;

import cn.gluttonous.bank.manager.Manager;
import cn.gluttonous.bank.manager.impl.ManagerImpl;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.view.RegisteredView;

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
public class Registered extends RegisteredView {


    /**
     * 对{registrationButton}进行监听
     *
     * @param registrationButton
     */
    @Override
    public void register(JButton registrationButton) {

        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkUserName()){
                    if(checkPassword()) {
                        String userName = userText.getText();
                        String password = new String(passwordText.getPassword());

                        int flag = ManagerImpl.getInstance(new UserBean(userName, password)).register();

                        if (flag == 0) {
                            JOptionPane.showMessageDialog(null, "用户已存在!", "提示消息", JOptionPane.WARNING_MESSAGE);
                        } else if (flag == -1) {
                            JOptionPane.showMessageDialog(null, "请重新注册!", "提示消息", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "注册成功!", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                            new Login();
                            dispose();
                        }
                    }
                }
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
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        Registered registered = new Registered();
    }
}
