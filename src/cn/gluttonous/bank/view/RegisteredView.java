package cn.gluttonous.bank.view;

import cn.gluttonous.bank.view.tool.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/13
 * @Version: 1.0
 **/
public abstract class RegisteredView extends JFrame {

    public JTextField userText;
    public JPasswordField  passwordText;
    public JPasswordField  passwordText_1;

    public RegisteredView(){

        FrameUtil.initFrame(this,750,500);
        this.setTitle("注册");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        this.add(jPanel);
        this.initPanel(jPanel);
    }

    public void initPanel(JPanel jPanel){
        //大标题
        JLabel label = new JLabel("欢迎加入 MYB 银行");
        Font font = new Font("宋体",Font.BOLD,42);
        label.setFont(font);
        jPanel.add(label);
        label.setBounds(175,30,700,80);


        Font font_1 = new Font("宋体",Font.PLAIN,24);

        //用户名条
        JLabel useLabel = new JLabel("用户名：");
        useLabel.setFont(font_1);
        jPanel.add(useLabel);
        useLabel.setBounds(140,130,110,45);

        this.userText = new JTextField(40);
        userText.setFont(font_1);
        jPanel.add(userText);
        userText.setBounds(290,130,300,45);

        //密码条
        JLabel passwordLabel = new JLabel("密  码：");
        jPanel.add(passwordLabel);
        passwordLabel.setFont(font_1);
        passwordLabel.setBounds(140,220,110,45);

        this.passwordText = new JPasswordField(40);
        passwordText.setFont(font_1);
        jPanel.add(passwordText);
        passwordText.setBounds(290,220,300,45);

        //确认密码条
        JLabel passwordLabel_1 = new JLabel("确定密码：");
        jPanel.add(passwordLabel_1);
        passwordLabel_1.setFont(font_1);
        passwordLabel_1.setBounds(140,310,120,45);

        this.passwordText_1 = new JPasswordField(40);
        passwordText_1.setFont(font_1);
        jPanel.add(passwordText_1);
        passwordText_1.setBounds(290,310,300,45);

        // 创建取消按钮
        JButton cancelButton = new JButton("取 消");
        cancelButton.setFont(font_1);
        jPanel.add(cancelButton);
        cancelButton.setBounds(160, 380, 120, 65);

        //监听cancel按钮
        this.cancel(cancelButton);

        // 创建登录按钮
        JButton registrationButton = new JButton("注 册");
        registrationButton.setFont(font_1);
        jPanel.add(registrationButton);
        registrationButton.setBounds(500, 380, 120, 65);

        //监听 loginButton 按钮
        this.register(registrationButton);
    }

    /**
     * 检查两次密码是否相同
     */
    public boolean checkPassword(){
        if(!new String(passwordText.getPassword()).equals(new String(passwordText_1.getPassword()))){
            JOptionPane.showMessageDialog(null, "两次密码不相同，请重新输入!","提示消息",JOptionPane.WARNING_MESSAGE);
            clear();
            return false;
        }
        return true;
    }

    /**
     * 检查两用户名
     */
    public boolean checkUserName(){
        if(userText.getText().equals("")){
            JOptionPane.showMessageDialog(null, "用户名不能为空!","提示消息",JOptionPane.WARNING_MESSAGE);
            this.clear();
            return false;
        }
        return true;
    }


    private void clear() {
        userText.setText("");
        passwordText.setText("");
        passwordText_1.setText("");
    }

    /**
     * 对{loginButton}进行监听
     * @param registrationButton
     */
    public abstract void register(JButton registrationButton);

    /**
     * 对{cancelButton}进行监听
     * @param cancelButton
     */
    public abstract void cancel(JButton cancelButton);

}
