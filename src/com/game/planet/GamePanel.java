package com.game.planet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{

    //定义全局常量，游戏窗口的宽度和高度
    public static final int FRAME_WIDTH = 600;

    public static final int FRAME_HEIGHT = 800;

    public GamePanel(){
        //设置背景颜色R,G,B参数,
        this.setBackground(new Color(0,0,0));
        //设置面板的默认大小
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        //设定焦点在本窗体并付与监听对象
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameFrame.gameHandler.draw(g);
    }

    //按下键盘事件
    @Override
    public void keyPressed(KeyEvent e) {
        GameFrame.gameHandler.keyPressed(e);
    }

    //释放键盘事件
    @Override
    public void keyReleased(KeyEvent e) {
        GameFrame.gameHandler.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
