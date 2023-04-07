package com.example.gomoku.logic;

import com.example.gomoku.dao.ChessBoardInfo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @Description 计时器
 * @Author 住京华
 * @Date 2022/11/1-下午 02:27
 */
public class TimeCounter {
    public static int EachTime = 60;//每一步的时间
    public static int TotalTime = 0;//游戏总时间
    public static Timeline timeline;//计时器
    private Text textTime;
    
    //计时器
    public void Timer() {
        textTime = new Text();
        textTime.setX(50);
        textTime.setY(35);
        textTime.setFont(Font.font(30));
        ChessBoardInfo.pane.getChildren().add(textTime);
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> TimeText()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    //计时器文本
    private void TimeText() {
        EachTime--;
        TotalTime++;
        if (EachTime == 0) timeline.stop();
        String s1 = TotalTime + "";
        String s = EachTime + "";
        if (ChessBoardInfo.isBlack && !ChessBoardInfo.isAi) {
            textTime.setText("当前:黑棋" + "         游戏总时间：" + s1 + " 秒" + "  倒计时：" + s + " 秒");
        } else textTime.setText("当前:白棋" + "         游戏总时间：" + s1 + " 秒" + "  倒计时：" + s + " 秒");
        if (ChessBoardInfo.isAi) {
            textTime.setText("人类:黑棋" + "         游戏总时间：" + s1 + " 秒" + "  倒计时：" + s + " 秒");
        }
        Pointer();//指示棋子颜色
    }
    
    //指示当前棋子颜色
    private void Pointer() {
        Circle circle1 = new Circle(18);
        circle1.setCenterX(200);
        circle1.setCenterY(25);
        if (ChessBoardInfo.isBlack) {
            circle1.setFill(Color.BLACK);
        } else circle1.setFill(Color.WHITE);
        circle1.setStroke(Color.BLACK);
        ChessBoardInfo.pane.getChildren().add(circle1);
    }
}
