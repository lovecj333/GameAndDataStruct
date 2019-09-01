package com.game.tanknet;

import java.awt.*;
import java.util.List;

public class Missile {

    public static final int SPEED = 10;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private int x;
    private int y;
    private Direction dir;
    private boolean good;
    private boolean live = true;
    private TankClient tc;

    public Missile(int x, int y, boolean good, Direction dir, TankClient tc) {
        this.x = x;
        this.y = y;
        this.good = good;
        this.dir = dir;
        this.tc = tc;
    }

    public void draw(Graphics g){
        if(!live){
            this.tc.missiles.remove(this);
            return;
        }
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
        }
    }

    public boolean isLive() {
        return live;
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void hitTank(Tank t){
        if(this.good == t.isGood()){
            return;
        }
        if(t.isLive() && this.getRect().intersects(t.getRect())){
            if(t.isGood()){
                t.setBlood(t.getBlood() - 20);
                if(t.getBlood() <= 0){
                    t.setLive(false);
                }
            }else{
                t.setLive(false);
            }
            this.live = false;
            Explode e = new Explode(x, y, tc);
            this.tc.explodes.add(e);
        }
    }

    public void hitTanks(List<Tank> tanks){
        for (int i = 0; i < tanks.size(); i++) {
            hitTank(tanks.get(i));
        }
    }
}
