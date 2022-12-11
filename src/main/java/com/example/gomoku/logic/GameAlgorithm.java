package com.example.gomoku.logic;

import com.example.gomoku.control.Control;
import com.example.gomoku.gui.GameStage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @Description 逻辑层
 * @Author 住京华
 * @Date 2022/10/25-下午 05:52
 */
public class GameAlgorithm {
    public static int[][] chess;//储存下棋信息，其中黑棋数值为1，白棋数值为-1
    public static int RegretNumber = 0;
    public static int goalX;//电脑落子位置
    public static int goalY;//电脑落子位置
    public int[][] score;//分数数组
    int tupleScoreTmp;
    int maxScore;
    private Text text;//游戏结束文本
    
    public GameAlgorithm() {
        chess = new int[15][15];
        score = new int[15][15];//初始化得分为0
    }//左上角开始为（0，0）右下角为（14，14）
    
    //垂直方向
    private boolean WinY(int x, int y) {
        int count = 1;
        //往下寻找
        for (int i = y + 1; i < chess[0].length; ++i) {
            if (chess[x][i] == chess[x][y]) {
                count++;
            } else break;
        }
        //往上寻找
        for (int i = y - 1; i >= 0; --i) {
            if (chess[x][i] == chess[x][y]) {
                count++;
            } else break;
        }
        return count >= 5;
    }
    
    //水平方向
    private boolean WinX(int x, int y) {
        int count = 1;
        //往右寻找
        for (int i = x + 1; i < chess.length; ++i) {
            if (chess[i][y] == chess[x][y]) {
                count++;
            } else break;
        }
        //往左寻找
        for (int i = x - 1; i >= 0; --i) {
            if (chess[i][y] == chess[x][y]) {
                count++;
            } else break;
        }
        return count >= 5;
    }
    
    //主对角线方向
    private boolean WinDigMain(int x, int y) {
        int count = 1;
        for (int i = x - 1, j = y + 1; i >= 0 && j < chess.length; --i, ++j) {
            if (chess[i][j] == chess[x][y]) {
                count++;
            } else break;
        }
        for (int i = x + 1, j = y - 1; i < chess.length && j >= 0; ++i, --j) {
            if (chess[i][j] == chess[x][y]) {
                count++;
            } else break;
        }
        return count >= 5;
    }
    
    //对角线方向
    private boolean WinDig(int x, int y) {
        int count = 1;
        for (int i = x + 1, j = y + 1; i < chess.length && j < chess.length; ++i, ++j) {
            if (chess[i][j] == chess[x][y]) {
                count++;
            } else break;
        }
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; --i, --j) {
            if (chess[i][j] == chess[x][y]) {
                count++;
            } else {
                break;
            }
        }
        return count >= 5;
    }
    
    //人机对战
    public void GetScore() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                score[i][j] = 0;
            }
        }
        goalX = 0;
        goalY = 0;
        maxScore = -1;
        //人类棋子数
        int humanChessNum = 0;
        //机器棋子数
        int machineChessNum = 0;
        tupleScoreTmp = 0;
        //扫描横向
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                int k = j;
                while (k < j + 5) {
                    if (chess[i][k] == -1) machineChessNum++;
                    else if (chess[i][k] == 1) humanChessNum++;
                    k++;
                }
                //得分临时变量
                tupleScoreTmp = tupleScore(humanChessNum, machineChessNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++) {
                    score[i][k] += tupleScoreTmp;
                }
                //置零
                humanChessNum = 0;//五元组中的黑棋数量
                machineChessNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }
        
        //扫描纵向
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                int k = j;
                while (k < j + 5) {
                    if (chess[k][i] == -1) machineChessNum++;
                    else if (chess[k][i] == 1) humanChessNum++;
                    k++;
                }
                //得分临时变量
                tupleScoreTmp = tupleScore(humanChessNum, machineChessNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++) {
                    score[k][i] += tupleScoreTmp;
                }
                //置零
                humanChessNum = 0;//五元组中的黑棋数量
                machineChessNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }
        
        //右上到左下上一半
        for (int i = 14; i >= 4; i--) {
            for (int k = i, j = 0; j < 15 && k >= 0; j++, k--) {
                int m = k;
                int n = j;
                while (m > k - 5 && k - 5 >= -1) {
                    if (chess[m][n] == -1) machineChessNum++;
                    else if (chess[m][n] == 1) humanChessNum++;
                    
                    m--;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k - 5) {
                    tupleScoreTmp = tupleScore(humanChessNum, machineChessNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m > k - 5; m--, n++) {
                        score[m][n] += tupleScoreTmp;
                    }
                }
                
                //置零
                humanChessNum = 0;//五元组中的黑棋数量
                machineChessNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
                
            }
        }
        //右上角到左下角下侧
        for (int i = 1; i < 15; i++) {
            for (int k = i, j = 14; j >= 0 && k < 15; j--, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chess[n][m] == -1) machineChessNum++;
                    else if (chess[n][m] == 1) humanChessNum++;
                    
                    m++;
                    n--;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessNum, machineChessNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n--) {
                        score[n][m] += tupleScoreTmp;
                    }
                }
                //置零
                humanChessNum = 0;//五元组中的黑棋数量
                machineChessNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
                
            }
        }
        //左上角到右下角上侧
        for (int i = 0; i < 11; i++) {
            for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chess[m][n] == -1) machineChessNum++;
                    else if (chess[m][n] == 1) humanChessNum++;
                    
                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessNum, machineChessNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        score[m][n] += tupleScoreTmp;
                    }
                }
                
                //置零
                humanChessNum = 0;//五元组中的黑棋数量
                machineChessNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
                
            }
        }
        //左上角到右下角下侧
        for (int i = 1; i < 11; i++) {
            for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chess[n][m] == -1) machineChessNum++;
                    else if (chess[n][m] == 1) humanChessNum++;
                    
                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessNum, machineChessNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        score[n][m] += tupleScoreTmp;
                    }
                }
                
                //置零
                humanChessNum = 0;//五元组中的黑棋数量
                machineChessNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
                
            }
        }
        //从空位置中找到得分最大的位置
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (chess[i][j] == 0 && score[i][j] > maxScore) {
                    goalX = i;
                    goalY = j;
                    maxScore = score[i][j];
                }
            }
        }
    }
    
    //判断输赢
    public boolean WinGame(int x, int y) {
        return WinY(x, y) || WinX(x, y) || WinDigMain(x, y) || WinDig(x, y);
    }
    
    //停止游戏
    public void StopGame(boolean isBlack) {
        if (isBlack && !GameStage.isAi) {
            text = new Text(850, 400, "游戏结束 黑棋胜");
        }
        if (!isBlack && !GameStage.isAi) {
            text = new Text(850, 400, "游戏结束 白棋胜");
        }
        if (GameStage.isAi && !isBlack) {
            text = new Text(850, 400, "游戏结束 电脑胜");
        }
        if (GameStage.isAi && isBlack) {
            text = new Text(850, 400, "游戏结束 玩家胜");
        }
        text.setFont(Font.font(60));
        GameStage.pane.getChildren().add(text);
    }
    
    //重新开始
    public void ReStartGame() {
        GameStage.isBlack = true;//黑棋先手
        TimeCounter.EachTime = 60;//计时器重置
        TimeCounter.TotalTime = 0;//重置计时器
        TimeCounter.timeline.play();//启动计时器
        GameStage.pane.getChildren().removeAll(GameStage.circle);//移除所有棋子对象
        GameStage.pane.getChildren().remove(text);
        //重新初始化棋子数组
        for (int i = 0; i < chess[0].length; ++i) {
            for (int j = 0; j < chess.length; ++j) {
                chess[i][j] = 0;
            }
        }
    }
    
    //悔棋
    public void RegretChess() {
        if (GameStage.isAi){
            Hnm();
            return;
        }
        RegretNumber++;
        //只能悔棋一次
        if (RegretNumber == 1) {
            GameStage.pane.getChildren().remove(GameStage.circle[GameStage.ChessNumber - 1]);//移除棋子
            TimeCounter.EachTime = 60;
            GameStage.isBlack = !GameStage.isBlack;//重新开始
            chess[Control.x][Control.y] = 0;//棋子数组重置为0
        }
    }
    public void Hnm() {
        Text text = new Text("人机对战悔个jb");
        text.setFont(Font.font(20));
        text.setX(150);
        text.setY(80);
        Pane pane1 = new Pane();
        pane1.getChildren().add(text);
        Scene scene = new Scene(pane1, 400, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    //赋分数
    private int tupleScore(int humanNum, int machineNum) {
        //既有人类落子，又有机器落子，判分为0
        if (humanNum > 0 && machineNum > 0) {
            return 0;
        }
        //全部为空，没有落子，判分为7
        if (humanNum == 0 && machineNum == 0) {
            return 7;
        }
        //机器落1子，判分为35
        if (machineNum == 1) {
            return 35;
        }
        //机器落2子，判分为800
        if (machineNum == 2) {
            return 800;
        }
        //机器落3子，判分为15000
        if (machineNum == 3) {
            return 15000;
        }
        //机器落4子，判分为800000
        if (machineNum == 4) {
            return 800000;
        }
        //人类落1子，判分为15
        if (humanNum == 1) {
            return 15;
        }
        //人类落2子，判分为400
        if (humanNum == 2) {
            return 400;
        }
        //人类落3子，判分为1800
        if (humanNum == 3) {
            return 1800;
        }
        //1人类落4子，判分为100000
        if (humanNum == 4) {
            return 100000;
        }
        return -1;
    }
}
