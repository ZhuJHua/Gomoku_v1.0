package com.example.gomoku;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description 服务端
 * @Author 住京华
 * @Date 2022/11/25
 */
public class Tcp_Server {
    //服务端监听
    ServerSocket serverSocket;
    //客户端与输出流绑定
    ConcurrentHashMap<Object, Object> clientDataMap = new ConcurrentHashMap<>();

    ConcurrentHashMap<Object, Object> clientInMap = new ConcurrentHashMap<>();
    //客户端与客户名绑定
    ConcurrentHashMap<Object, Object> clientNameMap = new ConcurrentHashMap<>();


    public void creatServer(int port) {

        //连接到服务器的客户端数量
        AtomicLong clientNumber = new AtomicLong(1);
        new Thread(() -> {
            try {
                //在port端口监听
                serverSocket = new ServerSocket(port);
                ObjectInputStream objectInputStream = null;
                ObjectOutputStream objectOutputStream = null;
                while (true) {
                    //监听客户端
                    Socket clientSocket = serverSocket.accept();
                    //建立客户端输入输出流
                    objectInputStream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                    objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    //将客户端和输出流绑定
                    clientDataMap.put(clientSocket, objectOutputStream);
                    //将客户端和输入流绑定
                    clientInMap.put(clientSocket, objectInputStream);
                    //将客户端和客户名绑定
                    clientNameMap.put(clientSocket, ("玩家" + clientNumber.getAndIncrement()));
                    System.out.println(clientDataMap);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ).start();

    }

}
