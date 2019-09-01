package com.game.tanknet;

import java.awt.*;

public class Explode {

    private int x;
    private int y;
    private boolean live = true;
    private int[] diameters = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};
    private int step;
    private TankClient tc;

    public Explode(int x, int y, TankClient tc){
        this.x = x;
        this.y = y;
        this.tc = tc;
    }

    public void draw(Graphics g){
        if(!live){
            this.tc.explodes.remove(this);
            return;
        }
        if(step == diameters.length){
            live = false;
            step = 0;
            return;
        }
        Color c = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, diameters[step], diameters[step]);
        g.setColor(c);
        step++;
    }
}
