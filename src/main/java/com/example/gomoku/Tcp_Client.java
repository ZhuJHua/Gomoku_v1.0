package com.example.gomoku;

import java.io.*;
import java.net.Socket;

/**
 * @Description 客户端
 * @Author 住京华
 * @Date 2022/11/2-下午 02:40
 */
public class Tcp_Client {
    static boolean YN=false;
    public void cilent(String ipAddress, int port) throws IOException {
        Socket socket = new Socket(ipAddress, port);
        OutputStream outputStream=socket.getOutputStream();
        while (true){
                outputStream.write(Control.x);
                outputStream.write(Control.y);
                InputStream inputStream=socket.getInputStream();
                byte[] bytes=new byte[1024];
                int len=inputStream.read(bytes);
                System.out.println(new String(bytes,0,len));
       
        }
        
    }
}
