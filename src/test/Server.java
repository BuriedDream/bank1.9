package test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/16
 * @Version: 1.0
 **/
public class Server {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket client = null;
        String s = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            serverSocket = new ServerSocket(1412);
            client = serverSocket.accept();

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());

        }
        catch (IOException e) {
            System.out.println("客户端断开。。。");
            //e.printStackTrace();
        }

        try{
            while (true) {
               if(in.available()!=0){
                    s = in.readUTF();
                    out.writeUTF("你好！ 我是server");
                    System.out.println("服务器收到："+s);
                }

                Thread.sleep(500);
            }
        }
        catch (IOException e){
            e.printStackTrace();
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
        }


    }
}
