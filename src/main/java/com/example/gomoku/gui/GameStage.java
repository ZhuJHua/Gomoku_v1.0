package com.example.gomoku.gui;

import com.example.gomoku.dao.ChessBoardInfo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @Description 显示层
 * @Author 住京华
 * @Date 2022/10/25-上午 03:26
 */
public class GameStage extends Stage {
    
    public GameStage() {
        setResizable(false);//设置窗口不可变
        ChessBoardInfo.pane = new Pane();//面板对象
        Scene scene = new Scene(ChessBoardInfo.pane, ChessBoardInfo.BoardWidth, ChessBoardInfo.BoardHeight);//场景对象
        ImageView imageView = new ImageView(ChessBoardInfo.backgroundImg);//背景图片
        for (int i = 0; i < ChessBoardInfo.circle.length; ++i) ChessBoardInfo.circle[i] = new Circle();//初始化对象数组
        ChessBoardInfo.pane.getChildren().add(imageView);
        setScene(scene);
        setTitle("连珠游戏");//设置
        drawLine();//棋盘线
        drawButton();//设置按钮
        drawText();//文本
        show();
    }
    
    //游戏入口
    private void StartGame() {
        ChessBoardInfo.control.Mouse();//鼠标事件
        ChessBoardInfo.timeCounter.Timer();//启动计时器
        
    }
    
    private void StartAIGame() {
        ChessBoardInfo.isAi = true;
        ChessBoardInfo.control.Mouse();//鼠标事件
        ChessBoardInfo.timeCounter.Timer();//启动计时器
    }
    
    //绘制棋盘
    private void drawLine() {
        for (int i = 0; i < ChessBoardInfo.LineSize; i++) {
            Line line1 = new Line(ChessBoardInfo.LeftMar, ChessBoardInfo.TopMar + i * ChessBoardInfo.Space,
                    ChessBoardInfo.LeftMar + ChessBoardInfo.Space * 14,
                    ChessBoardInfo.TopMar + i * ChessBoardInfo.Space);
            Line line2 = new Line(ChessBoardInfo.LeftMar + i * ChessBoardInfo.Space, ChessBoardInfo.TopMar,
                    ChessBoardInfo.LeftMar + i * ChessBoardInfo.Space,
                    ChessBoardInfo.TopMar + ChessBoardInfo.Space * 14);
            ChessBoardInfo.pane.getChildren().add(line1);
            ChessBoardInfo.pane.getChildren().add(line2);
        }
    }
    
    //绘制按钮
    private void drawButton() {
        Button button = new Button("开始游戏");
        Button button1 = new Button("重新开始");
        Button button2 = new Button("悔棋");
        Button button3 = new Button("制作人员");
        Button button4 = new Button("人机对战");
        Button button5 = new Button("在线对战");
        button.setPrefSize(ChessBoardInfo.ButtonWidth, ChessBoardInfo.ButtonLength);
        button.setLayoutX(850);
        button.setLayoutY(600);
        button1.setPrefSize(ChessBoardInfo.ButtonWidth, ChessBoardInfo.ButtonLength);
        button1.setLayoutX(1050);
        button1.setLayoutY(600);
        button2.setPrefSize(ChessBoardInfo.ButtonWidth, ChessBoardInfo.ButtonLength);
        button2.setLayoutX(1250);
        button2.setLayoutY(600);
        button3.setPrefSize(ChessBoardInfo.ButtonWidth, ChessBoardInfo.ButtonLength);
        button3.setLayoutX(1250);
        button3.setLayoutY(700);
        button4.setPrefSize(ChessBoardInfo.ButtonWidth, ChessBoardInfo.ButtonLength);
        button4.setLayoutX(850);
        button4.setLayoutY(700);
        button5.setPrefSize(ChessBoardInfo.ButtonWidth, ChessBoardInfo.ButtonLength);
        button5.setLayoutX(1050);
        button5.setLayoutY(700);
        ChessBoardInfo.pane.getChildren().addAll(button, button1, button2, button3, button4, button5);
        //开始游戏检测
        button.setOnAction(e -> StartGame());
        button1.setOnAction(e -> ChessBoardInfo.gameAlgorithm.ReStartGame());
        button2.setOnAction(e -> ChessBoardInfo.gameAlgorithm.RegretChess());
        button3.setOnAction(e -> list());
        button4.setOnAction(e -> StartAIGame());
        button5.setOnAction(e -> ChessBoardInfo.netWork.login());
        
    }
    
    //绘制标题
    private void drawText() {
        ImageView imageView = new ImageView(ChessBoardInfo.titleImg);
        imageView.setX(900);
        imageView.setY(80);
        imageView.fitWidthProperty();
        ChessBoardInfo.pane.getChildren().add(imageView);
    }
    
    //制作人员名单
    private void list() {
        Pane pane1 = new Pane();
        Scene scene = new Scene(pane1, 400, 200);
        Text text1 = new Text("这是彩蛋");
        text1.setY(20);
        pane1.getChildren().add(text1);
        Stage stage1 = new Stage();
        stage1.setTitle("制作人员名单");
        stage1.setScene(scene);
        stage1.show();
    }
    
}
