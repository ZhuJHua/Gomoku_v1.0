package com.example.gomoku;

import java.io.*;
import java.net.Socket;

/**
 * @Description
 * @Author 住京华
 * @Date 2022/11/4-下午 02:20
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("43.139.115.5", 8888);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        while (true) {
            String str = bufferedReader.readLine();
            bufferedWriter.write(str);
            bufferedWriter.write("\n");
            bufferedWriter.flush();
        }
    }
}
