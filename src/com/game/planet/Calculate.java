package com.game.planet;

public class Calculate {

    public static boolean isRectCollision(int x1,int y1,int width1,int height1,
                                          int x2,int y2,int width2,int height2){
        return x2 + width2 > x1 && x2 < x1 + width1 && y2 + height2 > y1 && y2 < y1 + height1;
    }
}
