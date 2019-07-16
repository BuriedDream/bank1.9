package cn.gluttonous.bank.view;

import cn.gluttonous.bank.view.tool.FrameUtil;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/15
 * @Version: 1.0
 **/
public class DetailView extends JFrame {

    Object[][] tableDate = null;


    public DetailView(){
        FrameUtil.initFrame(this,750,500);
        this.setTitle("账 单 明 细");
        Container contentPane = this.getContentPane();

        String[] columnNames = {"日期","交易金额","交易余额","摘要"};
        JTable table=new JTable(tableDate,columnNames);
        contentPane.add(new JScrollPane(table));

    }

    public static void main(String[] args) {
        new DetailView();
    }

}
