package cn.gluttonous.bank.test;

import cn.gluttonous.bank.manager.Manager;
import cn.gluttonous.bank.manager.impl.ManagerImpl;
import cn.gluttonous.bank.model.MoneyBean;
import cn.gluttonous.bank.model.UserBean;
import cn.gluttonous.bank.util.AccountOverDrawnException;
import cn.gluttonous.bank.util.InvalidDepositException;
import cn.gluttonous.bank.view.HomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/14
 * @Version: 1.0
 **/
public class Home extends HomeView {

    UserBean userBean = null;
    public Home(UserBean userBean){
        this.userBean = userBean;
    }

    ManagerImpl manager = ManagerImpl.getInstance(userBean);
    /**
     * 监听 withdrawalsButton
     * 操作：取款
     *
     * @param withdrawalsButton
     */
    @Override
    public void withdrawals(JButton withdrawalsButton) {
        withdrawalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double money =Double.parseDouble(JOptionPane.showInputDialog("请输入取款金额："));
                    if(JOptionPane.showConfirmDialog(null,"确定取款 "+money+" 吗？") == 0){
                        manager.withdrawals(money);
                        JOptionPane.showMessageDialog(null, "余额："+manager.inquiry().getMoney(),"显示余额",JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch (InvalidDepositException ie){
                    JOptionPane.showMessageDialog(null, "存款金额不能为负！","输入金额错误",JOptionPane.ERROR_MESSAGE);
                }
                catch (AccountOverDrawnException ae){
                    JOptionPane.showMessageDialog(null, "余额不足！","提醒",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * 监听 transferButton
     * 操作：转账
     *
     * @param transferButton
     */
    @Override
    public void transfer(JButton transferButton) {
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("转账");
                try {
                    String name = JOptionPane.showInputDialog("请输入转账用户名：");
                    double money =Double.parseDouble(JOptionPane.showInputDialog("请输入转账金额："));
                    if(JOptionPane.showConfirmDialog(null,"确定转 "+money+" 给"+name+"吗？") == 0){
                        manager.transfer(name,new MoneyBean(money));
                        JOptionPane.showMessageDialog(null, "余额："+manager.inquiry().getMoney(),"显示余额",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (InvalidDepositException ie){
                    JOptionPane.showMessageDialog(null, "存款金额不能为负！","输入金额错误",JOptionPane.ERROR_MESSAGE);
                }
                catch (AccountOverDrawnException ae){
                    JOptionPane.showMessageDialog(null, "余额不足！","提醒",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * 监听 depositButton
     * 操作：存款
     *
     * @param depositButton
     */
    @Override
    public void deposit(JButton depositButton) {
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("存款");

                double money =Double.parseDouble(JOptionPane.showInputDialog("请输入存款金额："));
                try{
                    manager.deposit(money);
                    MoneyBean moneyBean = manager.inquiry();
                    JOptionPane.showMessageDialog(null, "余额："+moneyBean.getMoney(),"显示余额",JOptionPane.INFORMATION_MESSAGE);

                }
                catch (InvalidDepositException ie){
                    JOptionPane.showMessageDialog(null, "存款金额不能为负！","输入金额错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * 监听 inquiryButton
     * 操作：显示余额
     *
     * @param inquiryButton
     */
    @Override
    public void inquiry(JButton inquiryButton) {

        inquiryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoneyBean moneyBean = manager.inquiry();
                JOptionPane.showMessageDialog(null, "余额："+moneyBean.getMoney(),"显示余额",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * 监听 exitButton
     * 操作：退出系统
     *
     * @param exitButton
     */
    @Override
    public void exit(JButton exitButton) {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //完成写入操作
                manager.exitSystem();
                //System.out.println("退出");
                //System.exit(0);
            }
        });

    }
}
