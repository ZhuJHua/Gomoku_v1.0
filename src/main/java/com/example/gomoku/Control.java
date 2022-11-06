package com.example.gomoku;

import javafx.scene.paint.Color;

import static com.example.gomoku.GameStage.*;

/**
 * @Description 控制
 * @Author 住京华
 * @Date 2022/11/1-下午 02:41
 */
public class Control {
    static int x;//鼠标横坐标
    static int y;//鼠标纵坐标
    
    //绘制棋子
    public void drawChess(int x, int y) {
        circle[ChessNumber].setCenterX(LeftMar + x * Space);//中心点横坐标
        circle[ChessNumber].setCenterY(TopMar + y * Space);//中心点纵坐标
        circle[ChessNumber].setRadius(ChessRad);//棋子半径
        if (isBlack) {
            circle[ChessNumber].setFill(Color.BLACK);
            GameAlgorithm.chess[x][y] = 1;//黑子
        } else {
            circle[ChessNumber].setFill(Color.WHITE);
            GameAlgorithm.chess[x][y] = -1;//白子
        }
        isBlack = !isBlack;
        for (int i = 0; i < ChessNumber; ++i) {
            circle[i].setStroke(Color.BLACK);
        }
        circle[ChessNumber].setStroke(Color.RED);//外框颜色
        pane.getChildren().add(circle[ChessNumber++]);
        GameAlgorithm.RegretNumber = 0;//重置悔棋次数
    }
    
    //鼠标操作
    public void Mouse() {
        pane.setOnMouseClicked(e -> {
            //将鼠标位置转为（0，14）范围内的整数，浮动0.5范围作为边缘
            x = (int) Math.round(((e.getX() - LeftMar) / Space));
            y = (int) Math.round(((e.getY() - TopMar) / Space));
            Tcp_Client.YN=true;
            //边界检测且不能下在同一个地方
            if (e.getX() > LeftMar - ChessRad && e.getX() < LineSize * Space + ChessRad && e.getY() > TopMar - ChessRad
                    && e.getY() < LineSize * Space + ChessRad && GameAlgorithm.chess[x][y] == 0) {
                drawChess(x, y);//四舍五入
                Tcp_Client.YN=false;
                if (gameAlgorithm.WinGame(x, y)) {
                    TimeCounter.timeline.stop();//停止计时器
                    gameAlgorithm.StopGame(!isBlack);
                }
                
                //电脑操作
                if (isAi) {
                    gameAlgorithm.GetScore();
                    drawChess(GameAlgorithm.goalX, GameAlgorithm.goalY);
                    
                    if (gameAlgorithm.WinGame(GameAlgorithm.goalX, GameAlgorithm.goalY)) {
                        TimeCounter.timeline.stop();//停止计时器
                        gameAlgorithm.StopGame(!isBlack);
                    }
                }
                TimeCounter.EachTime = 60;//计时器重置
            }
        });
    }
    
}
