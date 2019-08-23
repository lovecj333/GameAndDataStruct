package com.game.tank;

import java.awt.*;

public class Missile {

    public static final int SPEED = 10;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private int x;
    private int y;
    private Direction dir;
    private boolean live = true;
    private TankClient tc;

    public Missile(int x, int y, Direction dir, TankClient tc) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tc = tc;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }

    private void move() {
        switch (dir) {
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
        }
        if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT){
            live = false;
            this.tc.missiles.remove(this);
        }
    }

    public boolean isLive() {
        return live;
    }
}
