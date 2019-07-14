package cn.gluttonous.bank.view.tool;

import javax.swing.*;
import java.awt.*;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/12
 * @Version: 1.0
 **/
public class FrameUtil {

    public static void initFrame(JFrame frame,int width, int height){
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension dimension = toolkit.getScreenSize();
        int x = (int) dimension.getWidth();
        int y = (int) dimension.getHeight();
        frame.setBounds((x - width)/2,(y - height)/2,width,height);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
