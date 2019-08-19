package com.game.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {

    public static final int SPEED = 5;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private int x;
    private int y;
    private Direction dir = Direction.STOP;
    private Direction ptDir = Direction.D;
    private TankClient tc;

    private boolean vk_up = false;
    private boolean vk_down = false;
    private boolean vk_left = false;
    private boolean vk_right = false;

    public Tank(int x, int y, TankClient tc) {
        this.x = x;
        this.y = y;
        this.tc = tc;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        int lineX1 = x + Tank.WIDTH/2;
        int lineY1 = y + Tank.HEIGHT/2;
        int lineX2, lineY2 = 0;
        switch(ptDir){
            case U:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
            case D:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
            case L:
                g.drawLine(lineX1, lineY1, x, y + Tank.HEIGHT/2);
                break;
            case R:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
            case LU:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
            case LD:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
            case RU:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
            case RD:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
            case STOP:
                g.drawLine(lineX1, lineY1, 0, 0);
                break;
        }

        move();
    }

    private void move(){
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
            case KeyEvent.VK_J:
                fire();
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
        }
        locateDirection();
    }

    public void fire(){
        //计算子弹的坐标 位置固定在坦克的中心
        int missileX = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
        int missileY = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
        Missile m = new Missile(missileX, missileY, dir);
        tc.m = m;
    }
}
