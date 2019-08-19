package com.game.planet;

import java.awt.*;

public class MyPlanet {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    //位置坐标
    public int x,y;
    //动作状态
    public boolean isLeft,isRight,isUp,isDown,isFire;
    //移动速度
    private int speed = 10;
    //子弹发射的cd时间,这里设置一秒钟产生5颗子弹
    private Timer fireCDTimer = new Timer(GameHandler.FPS / 5);

    public MyPlanet(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        //用正方形表示飞机
        g.drawRect(x, y, WIDTH, HEIGHT);
    }

    public void update(){
        //处理各方向的移动
        if(this.isLeft){
            this.move_left();
        }
        if(this.isRight){
            this.move_right();
        }
        if(this.isUp){
            this.move_up();
        }
        if(this.isDown){
            this.move_down();
        }
        if(this.isFire){
            this.fire();
        }
    }

    private void move_left(){
        this.x -= speed;
    }

    private void move_right(){
        this.x += speed;
    }

    private void move_up(){
        this.y -= speed;
    }

    private void move_down(){
        this.y += speed;
    }

    public void fire(){
        //产生一颗子弹，位置就在自己飞机的正前方
        //这里的+20和-5用来调整子弹的初始位置，让它从飞机的正前方打出来
        if(this.fireCDTimer.act()){
            GameFrame.gameHandler.bulletList.add(new Bullet(this.x + 20, this.y - 5));
        }
    }
}
