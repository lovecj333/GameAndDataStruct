package com.game.tank;

import java.awt.*;

public class Buff {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private int x;
    private int y;
    private boolean live = true;
    private int step;
    private TankClient tc;
    private int[][] pos = {{350,300},{360,300},{375,275},{400,200},{360,270},{365,290},{340,280}};

    public Buff(TankClient tc){
        this.x = pos[0][0];
        this.y = pos[0][1];
        this.tc = tc;
    }

    public void draw(Graphics g){
        if(!live){
            return;
        }
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }

    private void move(){
        step++;
        if(step == pos.length){
            step = 0;
        }
        this.x = pos[step][0];
        this.y = pos[step][1];
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
