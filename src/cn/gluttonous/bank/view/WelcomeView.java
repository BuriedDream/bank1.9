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
public abstract class WelcomeView extends JFrame {

    public WelcomeView(){

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

        JLabel label_1 = new JLabel("1、如果已经加入了 MYB 大家庭请直接登录；");
        JLabel label_2 = new JLabel("2、如果还未加入了 MYB 大家庭请先注册再登录；");
        JLabel label_3 = new JLabel("3、如果如果迷路了请点取消。");

        label_1.setFont(font_1);
        label_2.setFont(font_1);
        label_3.setFont(font_1);

        jPanel.add(label_1);
        jPanel.add(label_2);
        jPanel.add(label_3);

        label_1.setBounds(160,120,700,45);
        label_2.setBounds(160,180,700,45);
        label_3.setBounds(160,250,700,45);

        // 创建取消按钮
        JButton cancelButton = new JButton("退 出");
        cancelButton.setFont(font_1);
        jPanel.add(cancelButton);
        cancelButton.setBounds(130, 360, 120, 65);

        //监听cancel按钮
        this.exit(cancelButton);

        // 创建取消按钮
        JButton loginButton = new JButton("登 录");
        loginButton.setFont(font_1);
        jPanel.add(loginButton);
        loginButton.setBounds(310, 360, 120, 65);

        //监听login按钮
        this.login(loginButton);

        // 创建登录按钮
        JButton registrationButton = new JButton("注 册");
        registrationButton.setFont(font_1);
        jPanel.add(registrationButton);
        registrationButton.setBounds(490, 360, 120, 65);

        //监听 loginButton 按钮
        this.register(registrationButton);
    }

    /**
     * 对{loginButton}进行监听
     * @param loginButton
     */
    protected abstract void login(JButton loginButton);


    /**
     * 对{registrationButton}进行监听
     * @param registrationButton
     */
    public abstract void register(JButton registrationButton);

    /**
     * 对{cancelButton}进行监听
     * @param cancelButton
     */
    public abstract void exit(JButton cancelButton);

}
