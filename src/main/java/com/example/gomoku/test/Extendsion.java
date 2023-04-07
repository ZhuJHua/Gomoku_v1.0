package com.example.gomoku.test;

/**
 * @Description
 * @Author 住京华
 * @Date 2022/12/13
 */
public class Extendsion extends Base {
    public Extendsion() {
        System.out.println(add(2));
    }
    
    @Override
    public int add(int v) {
        i = i + 2 * v;
        return i;
    }
}
