package com.example.gomoku;

import javafx.scene.control.Label;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 客户端
 * @Author 住京华
 * @Date 2022/11/2-下午 02:40
 */
public class Tcp_Client {
    Socket socket;

    ObjectOutputStream objectOutputStream=null;
    ObjectInputStream objectInputStream=null;
    String hostAddress;

    {
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    public void connect(String ipAddress, int port) {
        try {
            socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
            NetWork.gridPane.add(new Label("网络错误，连接失败"), 1, 2);
            System.exit(0);
            e.printStackTrace();
        }
    }

    public void sendData(ChessInfo chessInfo) throws IOException {
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(chessInfo);
        objectOutputStream.flush();
        socket.shutdownOutput();
    }

    public ChessInfo receiveData() {
        ChessInfo temp;
        try (ObjectInputStream inputStream = objectInputStream = new ObjectInputStream
                (new BufferedInputStream(socket.getInputStream()))) {
            temp=(ChessInfo) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

}

