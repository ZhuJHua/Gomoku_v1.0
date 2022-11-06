package com.example.gomoku;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @Description 服务器端
 * @Author 住京华
 * @Date 2022/11/2-下午 02:40
 */
public class Tcp_Server {
    
    public void server() throws IOException {
        //监听5353端口
        ServerSocket serverSocket = new ServerSocket(5353);
        System.out.println("等待连接");
        //等待连接
        Socket socket = serverSocket.accept();
        System.out.println("连接成功");
        GameStage.control.Mouse();
        GameStage.timeCounter.Timer();
        //获取输入流
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[20];
        int read = inputStream.read(bytes);
        System.out.println(Arrays.toString(bytes));
    }
    public void receive(){
    
    }
}
