package com.example.gomoku;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Description socket
 * @Author 住京华
 * @Date 2022/11/2-下午 06:16
 */
public class NetWork {
    static String ipAddress;//ip地址
    static Text text=new Text();
    static int port;//端口
    Tcp_Server tcp_server=new Tcp_Server();//服务器
    Tcp_Client tcp_client=new Tcp_Client();//客机
    //绘制登录框
    public void login() {
        //建立文本框
        TextField textField = new TextField();
        TextField textField1 = new TextField();
        //添加按钮
        Button button = new Button("确定");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(3);
        gridPane.add(new Label("IP地址: "), 0, 0);
        gridPane.add(textField, 1, 0);
        gridPane.add(new Label("端口号: "), 0, 1);
        gridPane.add(textField1, 1, 1);
        gridPane.add(button, 1, 3);
        textField.setAlignment(Pos.BOTTOM_RIGHT);
        textField1.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setHalignment(button, HPos.RIGHT);
        gridPane.setAlignment(Pos.CENTER);
        //按钮事件
        button.setOnAction(e -> {
            ipAddress = textField.getText();
            port = Integer.parseInt(textField1.getText());
            try {
                tcp_client.cilent(ipAddress,port);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Scene scene = new Scene(gridPane, 400, 200);
        Stage stage = new Stage();
        stage.setTitle("登录");
        stage.setScene(scene);
        stage.show();
    }
}
