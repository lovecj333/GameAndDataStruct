package com.game.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class Tank {

    public static final int SPEED = 5;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private static Random r = new Random();
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    private Direction dir;
    private Direction ptDir = Direction.D;
    private boolean good;
    private boolean live = true;
    private int step = r.nextInt(12) + 3;
    private TankClient tc;

    private boolean vk_up = false;
    private boolean vk_down = false;
    private boolean vk_left = false;
    private boolean vk_right = false;

    public Tank(int x, int y, boolean good, Direction dir, TankClient tc) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.good = good;
        this.dir = dir;
        this.tc = tc;
    }

    public void draw(Graphics g){
        if(!live){
            if(!good){
                this.tc.enemyTanks.remove(this);
            }
            return;
        }
        Color c = g.getColor();
        if(good){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.BLUE);
        }
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        int lineX1 = x + Tank.WIDTH/2;
        int lineY1 = y + Tank.HEIGHT/2;
        int lineX2 = 0;
        int lineY2 = 0;
        switch(ptDir){
            case U:
                lineX2 = x + Tank.WIDTH/2;
                lineY2 = y;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
            case D:
                lineX2 = x + Tank.WIDTH/2;
                lineY2 = y + Tank.HEIGHT;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
            case L:
                lineX2 = x;
                lineY2 = y + Tank.HEIGHT/2;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
            case R:
                lineX2 = x + Tank.WIDTH;
                lineY2 = y + Tank.HEIGHT/2;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
            case LU:
                lineX2 = x;
                lineY2 = y;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
            case LD:
                lineX2 = x;
                lineY2 = y + Tank.HEIGHT;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
            case RU:
                lineX2 = x + Tank.WIDTH;
                lineY2 = y;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
            case RD:
                lineX2 = x + Tank.WIDTH;
                lineY2 = y + Tank.HEIGHT;
                g.drawLine(lineX1, lineY1, lineX2, lineY2);
                break;
        }

        move();
    }

    private void move(){
        this.oldX = x;
        this.oldY = y;
        switch(dir){
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case LU:
                x -= SPEED;
                y -= SPEED;
                break;
            case LD:
                x -= SPEED;
                y += SPEED;
                break;
            case RU:
                x += SPEED;
                y -= SPEED;
                break;
            case RD:
                x += SPEED;
                y += SPEED;
                break;
            case STOP:
                break;
        }
        if(this.dir != Direction.STOP){
            this.ptDir = this.dir;
        }
        if(x < 0){
            x = 0;
        }
        if(y < 30){
            y = 30;
        }
        if(x + Tank.WIDTH > TankClient.GAME_WIDTH){
            x = TankClient.GAME_WIDTH - Tank.WIDTH;
        }
        if(y + Tank.HEIGHT > TankClient.GAME_HEIGHT){
            y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
        }
        if(!good){
            if(step == 0){
                step = r.nextInt(12) + 3;
                //EnemyTank每次move之后都随机方向加入step是为了转方向平缓一些
                Direction[] dirs = Direction.values();
                this.dir = dirs[r.nextInt(dirs.length)];
            }
            step--;
            if(r.nextInt(40) > 38){
                this.fire();
            }
        }
    }

    private void locateDirection(){
        if(vk_up && !vk_down && !vk_left && !vk_right){
            dir = Direction.U;
        }else if(!vk_up && vk_down && !vk_left && !vk_right){
            dir = Direction.D;
        }else if(!vk_up && !vk_down && vk_left && !vk_right){
            dir = Direction.L;
        }else if(!vk_up && !vk_down && !vk_left && vk_right){
            dir = Direction.R;
        }else if(vk_up && !vk_down && vk_left && !vk_right){
            dir = Direction.LU;
        }else if(!vk_up && vk_down && vk_left && !vk_right){
            dir = Direction.LD;
        }else if(vk_up && !vk_down && !vk_left && vk_right){
            dir = Direction.RU;
        }else if(!vk_up && vk_down && !vk_left && vk_right){
            dir = Direction.RD;
        }else if(!vk_up && !vk_down && !vk_left && !vk_right){
            dir = Direction.STOP;
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_W:
                vk_up = true;
                break;
            case KeyEvent.VK_S:
                vk_down = true;
                break;
            case KeyEvent.VK_A:
                vk_left = true;
                break;
            case KeyEvent.VK_D:
                vk_right = true;
                break;
        }
        locateDirection();
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_W:
                vk_up = false;
                break;
            case KeyEvent.VK_S:
                vk_down = false;
                break;
            case KeyEvent.VK_A:
                vk_left = false;
                break;
            case KeyEvent.VK_D:
                vk_right = false;
                break;
            case KeyEvent.VK_J:
                fire();
                break;
            case KeyEvent.VK_U:
                superFire();
                break;
        }
        locateDirection();
    }

    public void fire(){
        if(!live){
            return;
        }
        //计算子弹的坐标 位置固定在坦克的中心
        int missileX = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
        int missileY = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
        Missile m = new Missile(missileX, missileY, good, ptDir, this.tc);
        tc.missiles.add(m);
    }

    public void superFire(){
        if(!live){
            return;
        }
        Direction[] dirs = Direction.values();
        int missileX = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
        int missileY = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
        for (int i = 0; i < 8; i++) {
            Missile m = new Missile(missileX, missileY, good, dirs[i], this.tc);
            tc.missiles.add(m);
        }
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    private void stay(){
        this.x = oldX;
        this.y = oldY;
    }

    public void collidesWithTank(Tank t){
        if(this != t && this.getRect().intersects(t.getRect())){
            this.stay();
            t.stay();
        }
    }

    public void collidesWithTanks(List<Tank> tanks){
        for (int i = 0; i < tanks.size(); i++) {
            collidesWithTank(tanks.get(i));
        }
    }

    public void collidesWithWall(Wall w){
        if(this.getRect().intersects(w.getRect())){
            this.stay();
        }
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isGood() {
        return good;
    }
}
