package com.example.gomoku.netservice;

import com.example.gomoku.dao.ChessInfo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 服务端
 * @Author 住京华
 * @Date 2022/11/30
 */
public class TcpServer {
    static ArrayList<Socket> clients = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        //创建线程池
        ExecutorService tp = Executors.newCachedThreadPool();
        serverSocket = new ServerSocket(8889);
        while (true) {
            try {
                //阻塞，直到有客户端连接
                clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
                //为客户端创建一个接收信息的线程
                tp.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    //接受客户端信息线程，每个客户端对应一个，用来获取客户端发送来的数据并广播到所有客户端
    static class HandleMsg implements Runnable {
        
        Socket clientSocket;
        ObjectOutputStream oos = null;
        ChessInfo chessInfo = null;
        
        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        
        @Override
        public void run() {
            //添加进客户端套接字池
            clients.add(clientSocket);
            //打印已经加入的玩家数
            System.out.println(clientSocket.getRemoteSocketAddress() + "已加入" + ",当前人数" + clients.size());
            ObjectInputStream is = null;
            try {
                //从InputStream中读取玩家发来的落子信息
                is = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                //发送给所有客户端
                sendMsg((ChessInfo) is.readObject());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (oos != null) {
                        oos.close();
                    }
                    clientSocket.close();
                    clients.remove(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        //广播发送方法
        void sendMsg(ChessInfo info) {
            for (int i = clients.size() - 1; i >= 0; --i) {
                try {
                    //将玩家落子信息发送给所有客户端
                    oos = new ObjectOutputStream(clients.get(i).getOutputStream());
                    oos.writeObject(info);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
