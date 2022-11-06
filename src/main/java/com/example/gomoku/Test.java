package com.example.gomoku;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @Description 测试
 * @Author 住京华
 * @Date 2022/10/25-上午 03:41
 */
public class Test extends Application {
    GameStage stage1;
    @Override
    public void start(Stage stage) {
         stage1= new GameStage();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
