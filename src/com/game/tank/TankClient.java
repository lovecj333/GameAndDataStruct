package com.game.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    Image offScreenImage = null;
    Tank myTank = new Tank(50, 50, this);
    Missile m;

    public void lauchFrame(){
        this.setLocation(400,300);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("TankWar");
        this.setBackground(Color.GREEN);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(new KeyMonitor());
        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g) {
        if(m != null){
            m.draw(g);
        }
        myTank.draw(g);
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gImage = offScreenImage.getGraphics();
        Color c = gImage.getColor();
        gImage.setColor(Color.GREEN);
        gImage.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
        gImage.setColor(c);
        paint(gImage);
        g.drawImage(offScreenImage, 0,0, null);
    }

    private class PaintThread implements Runnable{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.lauchFrame();
    }
}
