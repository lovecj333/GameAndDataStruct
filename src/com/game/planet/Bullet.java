package com.game.planet;

import java.awt.*;

public class Bullet {

    private int x,y;
    private int speed = 15;
    //判断子弹是由谁打出来的，默认是主角打出来的
    public boolean isEnemy =false;
    //子弹直径
    private int rdius = 15;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Bullet(int x, int y, boolean isEnemy){
        this(x, y);
        this.isEnemy = isEnemy;
    }

    public void draw(Graphics g){
        //绘制一个圆形
        g.fillOval(x, y, rdius, rdius);
    }

    public void update(){
        this.checkDestory();
        this.checkEnemyCollision();
        if(!this.isEnemy){
            //每帧让子弹上升
            this.y -= speed;
        }else{
            this.y += speed/2;
        }
    }

    public void checkDestory(){
        boolean outHeight = this.y > GamePanel.FRAME_HEIGHT || this.y < 0;
        if(outHeight){
            destory();
        }
    }

    private void destory(){
        //在集合类中移除自己，这样就没有任何对象有对此实例的引用了
        //jvm的gc会帮我们自动回收实例，并且释放资源
        GameHandler.bulletList.remove(this);
        //让图像到窗口外面去
        this.y =1000;
    }

    //遍历检测与敌机的碰撞
    private void checkEnemyCollision(){
        if(this.isEnemy){
            //敌机的子弹不进行检测
            return;
        }
        //检测这颗子弹与每一辆敌机是否碰撞
        for(int i=0;i<GameHandler.enemyPlanetList.size();i++){
            EnemyPlanet enemy = GameHandler.enemyPlanetList.get(i);
            boolean result = this.judgeEnemyCollision(enemy);
            if(result){
                break;
            }
        }
    }

    //判断这颗子弹与某一敌机是否碰撞
    private boolean judgeEnemyCollision(EnemyPlanet enemy){
        if(Calculate.isRectCollision(this.x, this.y, this.rdius, this.rdius,
                enemy.x, enemy.y, EnemyPlanet.ENEMY_WIDTH, EnemyPlanet.ENEMY_HEIGHT)){
            this.destory();
            enemy.destory();
            return true;
        }
        return false;
    }
}
