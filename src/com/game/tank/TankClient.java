package com.game.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    Image offScreenImage = null;
    Tank myTank = new Tank(50, 50, true, Direction.STOP,this);
    Wall wall1 = new Wall(100,200,20,150, this);
    Wall wall2 = new Wall(300,130,300,20, this);
    Buff buff = new Buff(this);
    List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    List<Missile> missiles = new ArrayList<>();

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
        for(int i = 0; i < 10; i++){
            enemyTanks.add(new Tank(100+(50*(i+1)),70,false, Direction.D, this));
        }
        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("missiles count : "+missiles.size(),10,50);
        g.drawString("explodes count : "+explodes.size(),10,70);
        g.drawString("enemyTanks count : "+enemyTanks.size(),10,90);
        g.drawString("myTank blood value : "+myTank.getBlood(), 10, 110);
        if(enemyTanks.size() <= 0){
            for(int i = 0; i < 5; i++){
                enemyTanks.add(new Tank(100+(70*(i+1)),70,false, Direction.D, this));
            }
        }
        for(int i = 0; i < missiles.size(); i++){
            Missile m = missiles.get(i);
            m.hitTanks(enemyTanks);
            m.hitTank(myTank);
            m.hitWall(wall1);
            m.hitWall(wall2);
            m.draw(g);
        }
        for(int i = 0; i < explodes.size(); i++){
            Explode e = explodes.get(i);
            e.draw(g);
        }
        for(int i = 0; i < enemyTanks.size(); i++){
            Tank enemyTank = enemyTanks.get(i);
            enemyTank.collidesWithWall(wall1);
            enemyTank.collidesWithWall(wall2);
            enemyTank.collidesWithTanks(enemyTanks);
            enemyTank.draw(g);
        }
        myTank.draw(g);
        myTank.collidesWithBuff(buff);
        wall1.draw(g);
        wall2.draw(g);
        buff.draw(g);
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
