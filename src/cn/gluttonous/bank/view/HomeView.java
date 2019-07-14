package cn.gluttonous.bank.view;

import cn.gluttonous.bank.view.tool.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/14
 * @Version: 1.0
 **/
public abstract class HomeView extends JFrame {

    public HomeView(){

        FrameUtil.initFrame(this,750,500);
        this.setTitle("MYB 银行");

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


        Font font_1 = new Font("宋体",Font.PLAIN,24);

        JLabel label_1 = new JLabel("感谢使用 MYB 银行");
        JLabel label_2 = new JLabel("您的一百分满意");
        JLabel label_3 = new JLabel("是MYB最大的成就！");

        label_1.setFont(font_1);
        label_2.setFont(font_1);
        label_3.setFont(font_1);

        jPanel.add(label_1);
        jPanel.add(label_2);
        jPanel.add(label_3);

        label_1.setBounds(260,140,250,45);
        label_2.setBounds(270,200,250,45);
        label_3.setBounds(265,270,250,45);

        // 创建退出按钮
        JButton cancelButton = new JButton("退 出");
        cancelButton.setFont(font_1);
        jPanel.add(cancelButton);
        cancelButton.setBounds(300, 360, 160, 70);

        //监听exit按钮
        this.exit(cancelButton);

        // 创建存款按钮
        JButton depositButton = new JButton("存 款");
        depositButton.setFont(font_1);
        jPanel.add(depositButton);
        depositButton.setBounds(55, 130, 120, 65);
        //监听取款按钮
        this.deposit(depositButton);

        // 创建取款按钮
        JButton withdrawalsButton = new JButton("取 款");
        withdrawalsButton.setFont(font_1);
        jPanel.add(withdrawalsButton);
        withdrawalsButton.setBounds(565, 130, 120, 65);

        //监听 loginButton 按钮
        this.withdrawals(withdrawalsButton);

        // 创建转账按钮
        JButton transferButton = new JButton("转 账");
        transferButton.setFont(font_1);
        jPanel.add(transferButton);
        transferButton.setBounds(565, 260, 120, 65);

        //监听 loginButton 按钮
        this.transfer(transferButton);


        // 创建取款按钮
        JButton inquiryButton = new JButton("余 额");
        inquiryButton.setFont(font_1);
        jPanel.add(inquiryButton);
        inquiryButton.setBounds(55, 260, 120, 65);

        //监听 loginButton 按钮
        this.inquiry(inquiryButton);


    }

    /**
     * 监听 withdrawalsButton
     * 操作：取款
     * @param withdrawalsButton
     */
    public abstract void withdrawals(JButton withdrawalsButton);

    /**
     * 监听 transferButton
     * 操作：转账
     * @param transferButton
     */
    public abstract void transfer(JButton transferButton);

    /**
     * 监听 depositButton
     * 操作：存款
     * @param depositButton
     */
    public abstract void deposit(JButton depositButton);

    /**
     * 监听 inquiryButton
     * 操作：显示余额
     * @param inquiryButton
     */
    public abstract void inquiry(JButton inquiryButton);

    /**
     * 监听 exitButton
     * 操作：退出系统
     * @param exitButton
     */
    public abstract void exit(JButton exitButton);
}
