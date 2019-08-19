package com.game.fivechess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FiveChessFrame extends JFrame implements MouseListener{

    int width = Toolkit.getDefaultToolkit().getScreenSize().width;      //屏幕宽度
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;    //屏幕高度
    int[][] allChess = new int[19][19];     //0没有 1黑色  2红色
    boolean isBlack = true;     //当前是不是黑色的棋子
    boolean isPlay = true;      //是否可以继续游戏

    public FiveChessFrame(){
        this.setTitle("Five Chess");
        this.setSize(500,500);
        this.setLocation((width - 500) / 2, (height - 500) / 2);
        this.setResizable(false);   //窗体大小不能改变
        this.addMouseListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        //背景
        g.setColor(Color.ORANGE);
        g.fillRect(0,0,500,500);
        //标题
        g.setColor(Color.BLACK);
        g.setFont(new Font("黑体", Font.BOLD, 20));
        g.drawString("游戏信息",30,60);
        //棋盘(每条线间隔20像素)
        for(int i = 0; i < 19; i++){
            g.drawLine(10,70 + 20 * i,370,70 + 20 * i);
            g.drawLine(10 + 20 * i,70,10 + 20 * i,430);
        }
        //棋盘点位
        g.fillOval(68,128,4,4);
        g.fillOval(308,128,4,4);
        g.fillOval(308,368,4,4);
        g.fillOval(68,368,4,4);
        g.fillOval(308,248,4,4);
        g.fillOval(188,128,4,4);
        g.fillOval(68,248,4,4);
        g.fillOval(188,368,4,4);
        g.fillOval(188,248,4,4);
        //绘制全部棋子
        for(int i = 0;i < 19;i++){
            for(int j=0;j < 19;j++){
                if(allChess[i][j] == 1){
                    int tempX = i * 20 + 10;
                    int tempY = j * 20 + 70;
                    g.setColor(Color.BLACK);
                    g.fillOval(tempX - 7, tempY - 7, 14,14);
                }else if(allChess[i][j] == 2){
                    int tempX = i * 20 + 10;
                    int tempY = j * 20 + 70;
                    g.setColor(Color.RED);
                    g.fillOval(tempX - 7, tempY - 7, 14,14);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x >= 10 && x <= 375 && y >= 70 && y <= 435){
            if(!isPlay){
                JOptionPane.showMessageDialog(this, "游戏已经结束");
                return;
            }
            //找到距离当前坐标点最近的数组下标
            int i = (x - 10) / 20;
            int j = (y - 70) / 20;
            //判断当前下标是否有棋子
            if(allChess[i][j] != 0){
                JOptionPane.showMessageDialog(this, "当前位置已经有棋子");
                return;
            }
            if(isBlack == true){
                allChess[i][j] = 1;
                isBlack = false;
            }else{
                allChess[i][j] = 2;
                isBlack = true;
            }
            //判断这个棋子是否和其他的棋子连成5个
            boolean isWin = this.checkWin(i,j);
            if(isWin){
                StringBuilder res = new StringBuilder();
                res.append("游戏结束").append(allChess[i][j] == 1 ? "黑方" : "红方").append("胜利");
                JOptionPane.showMessageDialog(this, res.toString());
                isPlay = false;
            }
            //此方法会调用paint方法
            this.repaint();
        }
    }

    private boolean checkWin(int i, int j){
        boolean isWin = false;
        //判断横向是否有5个棋子相连 纵坐标不变 j不变
        int count = 1;
        int color = allChess[i][j];
        int index = 1;
        while(i+index <= 18 && color == allChess[i+index][j]){
            count++;
            index++;
        }
        index = 1;
        while(i-index >= 0 && color == allChess[i-index][j]){
            count++;
            index++;
        }
        if(count >= 5){
            isWin = true;
        }
        return isWin;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
