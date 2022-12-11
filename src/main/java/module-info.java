/**
 * @Description
 * @Author 住京华
 * @Date 2022/10/25-上午 03:45
 */
module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    
    exports com.example.gomoku.gui;
    opens com.example.gomoku.gui to javafx.fxml;
    exports com.example.gomoku.netservice;
    opens com.example.gomoku.netservice to javafx.fxml;
    exports com.example.gomoku.logic;
    opens com.example.gomoku.logic to javafx.fxml;
    exports com.example.gomoku.test;
    opens com.example.gomoku.test to javafx.fxml;
    exports com.example.gomoku.dao;
    opens com.example.gomoku.dao to javafx.fxml;
    exports com.example.gomoku.control;
    opens com.example.gomoku.control to javafx.fxml;
}