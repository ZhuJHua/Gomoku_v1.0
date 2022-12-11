package com.example.gomoku.dao;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description 数据类
 * @Author 住京华
 * @Date 2022/11/23
 */
public class ChessInfo implements Serializable {
    //序列化
    @Serial
    private static final long serialVersionUID = -869412504066187108L;
    //坐标
    private int x;
    private int y;

    //构造函数
    public ChessInfo(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
