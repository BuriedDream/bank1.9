package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/16
 * @Version: 1.0
 **/
public class Client {

    public static void main(String[] args) {
        Socket client = null;
        String s = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            client = new Socket("localhost",1412);

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());

            out.writeUTF("你好 我是：client");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try{
            for (int i = 0; i < 10; i++) {
                out.writeUTF("client 发送："+i);
                s = in.readUTF();
                System.out.println("客户端接收："+s);
                Thread.sleep(100);
            }
        }
        catch (IOException e){
            System.out.println("连接失败！");
            //e.printStackTrace();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
//            try {
//                in.close();
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
