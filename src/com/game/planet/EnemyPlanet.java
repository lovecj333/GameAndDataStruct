package com.game.planet;

import java.awt.*;

public class EnemyPlanet {

    public static final int ENEMY_WIDTH = 50;
    public static final int ENEMY_HEIGHT = 50;
    public int x, y;
    private int speed = 3;
    //子弹发射的cd时间,这里设置一秒钟产生1颗子弹
    private Timer fireCDTimer = new Timer(GameHandler.FPS);

    public EnemyPlanet(){
        this.x = GameFrame.random.nextInt(GamePanel.FRAME_WIDTH);
        this.y = 1;
    }

    public void draw(Graphics g){
        g.drawRect(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
    }

    public void update(){
        this.trackMyPanel();
        this.checkDestory();
        this.fire();
    }

    private void trackMyPanel(){
        int mx = GameHandler.myPlanet.x;
        int my = GameHandler.myPlanet.y;
        if(this.x < mx){
            this.x += speed;
        }else{
            this.x -= speed;
        }
        this.y += speed;
    }

    public void checkDestory(){
        boolean outHeight = this.y > GamePanel.FRAME_HEIGHT;
        if(outHeight){
            destory();
        }
    }

    public void destory(){
        //在集合类中移除自己，这样就没有任何对象有对此实例的引用了
        //jvm的gc会帮我们自动回收实例，并且释放资源
        GameHandler.enemyPlanetList.remove(this);
        //让图像到窗口外面去
        this.y =1000;
    }

    public void fire(){
        //产生一颗子弹，位置就在自己飞机的正前方
        ////这里的+20和+55用来调整子弹的初始位置，让它从飞机的正前方打出来
        if(this.fireCDTimer.act()){
            GameFrame.gameHandler.bulletList.add(new Bullet(this.x + 20, this.y + 55, true));
        }
    }
}
