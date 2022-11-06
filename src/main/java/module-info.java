/**
 * @Description
 * @Author 住京华
 * @Date 2022/10/25-上午 03:45
 */
module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    
    
    opens com.example.gomoku to javafx.fxml;
    exports com.example.gomoku;
}