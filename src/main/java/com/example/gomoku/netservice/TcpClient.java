package com.example.gomoku.netservice;


import com.example.gomoku.dao.ChessInfo;
import javafx.scene.control.Label;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description 客户端
 * @Author 住京华
 * @Date 2022/11/30
 */
public class TcpClient {
    String hostAddress;
    Socket clientSocket = null;
    ObjectInputStream objectInputStream = null;

    {
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    public void connect(String ipAddress, int port) {
        try {
            clientSocket = new Socket(ipAddress, port);
        } catch (IOException e) {
            NetWork.gridPane.add(new Label("网络错误，连接失败"), 1, 2);
            System.exit(0);
            e.printStackTrace();
        }
    }

    public void sendChessInfo(ChessInfo chessInfo) {
        //创建新线程处理
        new getOutputStream(chessInfo).start();
    }

    public ChessInfo receiveChessInfo() {
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            return (ChessInfo) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //客户端线程，用于向服务端写入从客户端控制台输入的数据
    class getOutputStream extends Thread {
        ChessInfo chessInfo;

        public getOutputStream(ChessInfo chessInfo) {
            this.chessInfo = chessInfo;
        }

        @Override
        public void run() {
            ObjectOutputStream os = null;
            try {
                os = new ObjectOutputStream(clientSocket.getOutputStream());
                os.writeObject(chessInfo);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


