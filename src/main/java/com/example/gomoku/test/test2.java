package com.example.gomoku.test;

/**
 * @Description
 * @Author 住京华
 * @Date 2022/12/13
 */
public class test2 {
    
    public static void main(String[] args) {
        System.out.println(toUpper("rewrewjHJHK"));
    }
    
    public static String toUpper(String str) {
        for (int i = 0; i < str.length(); ++i) {
            str = str.toUpperCase();
        }
        return str;
    }
}
