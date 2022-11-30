package com.example.gomoku;

import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 服务线程
 * @Author 住京华
 * @Date 2022/11/25
 */
public class ServerThread extends Thread {
    //客户端套接口
    Socket clientSocket;
    //客户端与输出流绑定
    ConcurrentHashMap<Objects, Objects> clientDataMap;
    //客户端与客户名绑定
    ConcurrentHashMap<Objects, Objects> clientNameMap;

    public ServerThread(Socket clientSocket, ConcurrentHashMap<Objects, Objects> clientDataMap,
                        ConcurrentHashMap<Objects, Objects> clientNameMap) {
        this.clientSocket = clientSocket;
        this.clientDataMap = clientDataMap;
        this.clientNameMap = clientNameMap;
    }
}
