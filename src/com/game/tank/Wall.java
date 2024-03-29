package com.game.tank;

import java.awt.*;

public class Wall {

    private int x;
    private int y;
    private int w;
    private int h;
    private TankClient tc;

    public Wall(int x, int y, int w, int h, TankClient tc){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.tc = tc;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, w, h);
        g.setColor(c);
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, w, h);
    }
}
