package com.example.gomoku;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
    protected static final int TopMar = 50;//上间距
    protected static final int LeftMar = 50;//左边距
    protected static final int Space = 50; //内间距
    protected static final int LineSize = 15; //棋盘线条数
    protected static final int ChessRad = 20;//棋子半径
    protected static Circle[] circle = new Circle[361];//棋子对象数组
    protected static boolean isBlack = true;//黑棋还是白棋
    protected static Pane pane;//窗口
    protected static int ChessNumber = 0;//棋子计数器
    static boolean isAi = false;//是否是人机对战
    static boolean isOnline=false;//是否是在线对战
    static GameAlgorithm gameAlgorithm = new GameAlgorithm();//逻辑层对象
    static TimeCounter timeCounter = new TimeCounter();//计时器类对象
    static Control control = new Control();//控制类对象
    static NetWork netWork = new NetWork();//联机对象
    final int BoardWidth = 1400; //棋盘宽
    final int BoardHeight = 800; //棋盘高
    final int ButtonWidth = 100; //按钮宽
    final int ButtonLength = 50; //按钮高
    Image backgroundImg = new Image(String.valueOf(GameStage.class.getResource("/img/background.JPG")));//导入图片
    Image titleImg = new Image(String.valueOf(GameStage.class.getResource("/img/title.png")));
    
    public GameStage() {
        setResizable(false);//设置窗口不可变
        pane = new Pane();//面板对象
        Scene scene = new Scene(pane, BoardWidth, BoardHeight);//场景对象
        ImageView imageView = new ImageView(backgroundImg);//背景图片
        for (int i = 0; i < circle.length; ++i) circle[i] = new Circle();//初始化对象数组
        pane.getChildren().add(imageView);
        setScene(scene);
        setTitle("连珠游戏");//设置
        drawLine();//棋盘线
        drawButton();//设置按钮
        drawText();//文本
        show();
    }
    
    
    //游戏入口
    private void StartGame() {
        if (!isOnline){
            control.Mouse();//鼠标事件
            timeCounter.Timer();//启动计时器
        }

    }
    
    private void StartAIGame() {
        isAi = true;
        control.Mouse();//鼠标事件
        timeCounter.Timer();//启动计时器
    }
    
    //绘制棋盘
    private void drawLine() {
        for (int i = 0; i < LineSize; i++) {
            Line line1 = new Line(LeftMar, TopMar + i * Space, LeftMar + Space * 14, TopMar + i * Space);
            Line line2 = new Line(LeftMar + i * Space, TopMar, LeftMar + i * Space, TopMar + Space * 14);
            pane.getChildren().add(line1);
            pane.getChildren().add(line2);
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
        button.setPrefSize(ButtonWidth, ButtonLength);
        button.setLayoutX(850);
        button.setLayoutY(600);
        button1.setPrefSize(ButtonWidth, ButtonLength);
        button1.setLayoutX(1050);
        button1.setLayoutY(600);
        button2.setPrefSize(ButtonWidth, ButtonLength);
        button2.setLayoutX(1250);
        button2.setLayoutY(600);
        button3.setPrefSize(ButtonWidth, ButtonLength);
        button3.setLayoutX(1250);
        button3.setLayoutY(700);
        button4.setPrefSize(ButtonWidth, ButtonLength);
        button4.setLayoutX(850);
        button4.setLayoutY(700);
        button5.setPrefSize(ButtonWidth, ButtonLength);
        button5.setLayoutX(1050);
        button5.setLayoutY(700);
        pane.getChildren().addAll(button, button1, button2, button3, button4, button5);
        //开始游戏检测
        button.setOnAction(e -> StartGame());
        button1.setOnAction(e -> gameAlgorithm.ReStartGame());
        button2.setOnAction(e -> gameAlgorithm.RegretChess());
        button3.setOnAction(e -> list());
        button4.setOnAction(e -> StartAIGame());
        button5.setOnAction(e -> netWork.login());
    }
    
    //绘制标题
    private void drawText() {
        ImageView imageView = new ImageView(titleImg);
        imageView.setX(900);
        imageView.setY(80);
        imageView.fitWidthProperty();
        pane.getChildren().add(imageView);
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
