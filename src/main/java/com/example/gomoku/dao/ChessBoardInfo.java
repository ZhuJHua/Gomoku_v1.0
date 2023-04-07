package com.example.gomoku.dao;

import com.example.gomoku.control.Control;
import com.example.gomoku.gui.GameStage;
import com.example.gomoku.logic.GameAlgorithm;
import com.example.gomoku.logic.TimeCounter;
import com.example.gomoku.netservice.NetWork;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * @Description 棋盘信息
 * @Author 住京华
 * @Date 2022/12/11
 */
public class ChessBoardInfo {
    
    public static final int TopMar = 50;//上间距
    public static final int LeftMar = 50;//左边距
    public static final int Space = 50; //内间距
    public static final int LineSize = 15; //棋盘线条数
    public static final int ChessRad = 20;//棋子半径
    public static final int BoardWidth = 1400; //棋盘宽
    public static final int BoardHeight = 800; //棋盘高
    public static final int ButtonWidth = 100; //按钮宽
    public static final int ButtonLength = 50; //按钮高
    public static Circle[] circle = new Circle[361];//棋子对象数组
    public static boolean isBlack = true;//黑棋还是白棋
    public static Pane pane;//窗口
    public static int ChessNumber = 0;//棋子计数器
    public static boolean isAi = false;//是否是人机对战
    public static boolean isOnline = false;//是否是在线对战
    public static GameAlgorithm gameAlgorithm = new GameAlgorithm();//逻辑层对象
    public static TimeCounter timeCounter = new TimeCounter();//计时器类对象
    public static Control control = new Control();//控制类对象
    public static NetWork netWork = new NetWork();//联机对象
    public static Image backgroundImg =
            new Image(String.valueOf(GameStage.class.getResource("/img/background.JPG")));//导入图片
    public static Image titleImg = new Image(String.valueOf(GameStage.class.getResource("/img/title.png")));
}
