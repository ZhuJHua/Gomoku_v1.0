package com.example.gomoku.netservice;

import com.example.gomoku.gui.GameStage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @Description socket
 * @Author 住京华
 * @Date 2022/11/2-下午 06:16
 */
public class NetWork {
    static String ipAddress;//ip地址
    static int port;//端口
    public static TcpClient tcp_client = new TcpClient();//客机
    static GridPane gridPane = new GridPane();

    //绘制登录框
    public void login() {
        //建立文本框
        TextField textField = new TextField();
        TextField textField1 = new TextField();
        //添加按钮
        Button button = new Button("确定");

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
            //按下按钮后连接到服务器
            tcp_client.connect(ipAddress, port);
            //游戏类型设置为在线对战
            GameStage.isOnline=true;
            gridPane.add(new Label("连接成功" + " 本机IP:" + tcp_client.hostAddress), 1, 2);
        });
        Scene scene = new Scene(gridPane, 400, 200);
        Stage stage = new Stage();
        stage.setTitle("登录");
        stage.setScene(scene);
        stage.show();
    }
}
