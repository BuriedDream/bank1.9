package cn.gluttonous.bank.view;

import cn.gluttonous.bank.view.tool.FrameUtil;
import cn.gluttonous.bank.view.tool.ValidCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/12
 * @Version: 1.7
 **/
public abstract class LoginView  extends JFrame {


    public JTextField userText;
    public JPasswordField passwordText;
    public JTextField codeText;
    public ValidCode validCode = new ValidCode();

    public LoginView(){

        FrameUtil.initFrame(this,750,500);
        this.setTitle("登录");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        this.add(jPanel);
        this.initPanel(jPanel);
    }

    public void initPanel(JPanel jPanel){
        //大标题
        JLabel label = new JLabel("欢迎使用 MYB 银行");
        Font font = new Font("宋体",Font.BOLD,42);
        label.setFont(font);
        jPanel.add(label);
        label.setBounds(175,30,700,80);


        Font font_1 = new Font("宋体",Font.PLAIN,20);

        //用户名条
        JLabel useLabel = new JLabel("用户名：");
        useLabel.setFont(font_1);
        jPanel.add(useLabel);
        useLabel.setBounds(160,120,110,45);

        this.userText = new JTextField(40);
        userText.setFont(font_1);
        jPanel.add(userText);
        userText.setBounds(270,120,290,45);

        //密码条
        JLabel passwordLabel = new JLabel("密  码：");
        jPanel.add(passwordLabel);
        passwordLabel.setFont(font_1);
        passwordLabel.setBounds(160,210,110,45);

        this.passwordText = new JPasswordField(40);
        passwordText.setFont(font_1);
        jPanel.add(passwordText);
        passwordText.setBounds(270,210,290,45);

        //验证码条
        JLabel validCodeLabel = new JLabel("验证码：");
        jPanel.add(validCodeLabel);
        validCodeLabel.setFont(font_1);
        validCodeLabel.setBounds(160,300,100,45);

        this.codeText = new JTextField(40);
        codeText.setFont(font_1);
        jPanel.add(codeText);
        codeText.setBounds(270,300,180,45);

        jPanel.add(validCode);
        validCode.setBounds(480, 300, 100, 45);

        // 创建取消按钮
        JButton cancelButton = new JButton("取 消");
        cancelButton.setFont(font_1);
        jPanel.add(cancelButton);
        cancelButton.setBounds(200, 380, 100, 55);

        //监听cancel按钮
        this.cancel(cancelButton);

        // 创建登录按钮
        JButton loginButton = new JButton("登 录");
        loginButton.setFont(font_1);
        jPanel.add(loginButton);
        loginButton.setBounds(500, 380, 100, 55);

        //监听 loginButton 按钮
        this.login(loginButton);
    }

    //验证码的确认
    public boolean isValidCodeRight() {
        if(codeText == null) {
            return false;
        }
        else if(validCode == null) {
            return true;
        }
        else if(validCode.getCode() .equals(codeText.getText())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 检查验证码
     */
    public boolean checkValidCode(){
        if(codeText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入验证码!","提示消息",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!isValidCodeRight()) {
            JOptionPane.showMessageDialog(null, "验证码错误,请重新输入!","提示消息",JOptionPane.WARNING_MESSAGE);
            clear();
            return false;
        }
        return true;
    }

    /**
     * 检查用户名或密码
     */
    public boolean checkUserNameAndPassword(){
        if(userText.getText().equals("") || new String(passwordText.getPassword()).equals("")){
            JOptionPane.showMessageDialog(null, "用户名密码不能为空!","提示消息",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * 登录失败操作
     */
    public void loginFail(){
        JOptionPane.showMessageDialog(null, "用户名或密码错误!","登录失败",JOptionPane.WARNING_MESSAGE);
        clear();
    }

    private void clear() {
        userText.setText("");
        passwordText.setText("");
        codeText.setText("");
        validCode.nextCode();
    }

    /**
     * 对{loginButton}进行监听
     * @param loginButton
     */
    public abstract void login(JButton loginButton);

    /**
     * 对{cancelButton}进行监听
     * @param cancelButton
     */
    public abstract void cancel(JButton cancelButton);

}
