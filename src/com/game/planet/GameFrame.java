package com.game.planet;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameFrame extends JFrame{

    public static GameHandler gameHandler;//面板

    public static GamePanel gamePanel;//逻辑

    public static Random random = new Random();

    public GameFrame(){
        this.setTitle("planet game");
        gamePanel = new GamePanel();
        gameHandler = new GameHandler();
        //启动逻辑线程
        new Thread(gameHandler).start();
        //得到当前窗体[容器]的实例
        Container contentPane = this.getContentPane();
        //加载自定义的面板到窗体中，就好比在底板上再帖一层画面
        // 也就是说显示什么内容，是由我们加载那个［面板］来决定的，可以轻松
        // 的实现游戏中不同画面间的切换．
        contentPane.add(gamePanel);
        //自动整合窗体
        pack();
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        //令窗体接受关闭[事件]
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //让窗体显示
        gameFrame.setVisible(true);
    }
}
