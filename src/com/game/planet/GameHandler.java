package com.game.planet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class GameHandler implements Runnable{

    //游戏的FPS
    public static final int FPS =40;
    //玩家的飞机
    public static MyPlanet myPlanet = new MyPlanet(200, 630);
    //敌机集合
    public static List<EnemyPlanet> enemyPlanetList = new ArrayList<>();
    //子弹集合
    public static List<Bullet> bulletList = new ArrayList<>();
    //产生敌机计时器(2s一个敌机)
    public static Timer makeEnemyTimer = new Timer(FPS * 2);

    //处理每一帧的画图动作
    public void draw(Graphics g){
        //设置画笔颜色为蓝色
        g.setColor(Color.BLUE);
        myPlanet.draw(g);
        //遍历绘制敌机集合
        for(int i=0;i<enemyPlanetList.size();i++){
            EnemyPlanet enemy = enemyPlanetList.get(i);
            enemy.draw(g);
        }
        //遍历绘制子弹集合
        for (int i=0;i<bulletList.size();i++) {
            Bullet b = bulletList.get(i);
            b.draw(g);
        }
    }

    //处理每一帧的逻辑处理
    public void logical(){
        //产生敌机函数
        makeEnemy();
        //更新我的飞机的逻辑函数
        myPlanet.update();
        //遍历更新敌机集合的逻辑函数
        for(int i=0;i<enemyPlanetList.size();i++){
            EnemyPlanet enemy = enemyPlanetList.get(i);
            enemy.update();
        }
        //遍历更新子弹集合的逻辑函数
        for (int i=0;i<bulletList.size();i++) {
            Bullet b = bulletList.get(i);
            b.update();
        }
    }

    //产生敌机
    public void makeEnemy(){
        //每隔2秒产生一个敌人
        if(makeEnemyTimer.act()){
            enemyPlanetList.add(new EnemyPlanet());
        }
    }

    //按下键盘事件
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_A:
                myPlanet.isLeft = true;
                break;
            case KeyEvent.VK_D:
                myPlanet.isRight = true;
                break;
            case KeyEvent.VK_W:
                myPlanet.isUp = true;
                break;
            case KeyEvent.VK_S:
                myPlanet.isDown = true;
                break;
            case KeyEvent.VK_J:
                myPlanet.isFire = true;
                break;
        }
    }

    //释放键盘事件
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_A:
                myPlanet.isLeft = false;
                break;
            case KeyEvent.VK_D:
                myPlanet.isRight = false;
                break;
            case KeyEvent.VK_W:
                myPlanet.isUp = false;
                break;
            case KeyEvent.VK_S:
                myPlanet.isDown = false;
                break;
            case KeyEvent.VK_J:
                myPlanet.isFire = false;
                break;
        }
    }

    @Override
    public void run() {
        while(true){
            //执行一次逻辑处理函数
            this.logical();
            //使面板重绘一次,repaint方法会调用GamePanel中的paintComponent方法
            GameFrame.gamePanel.repaint();
            try {
                //线程每隔25ms休眠一次，即每秒执行40次此函数，即FPS=40
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
