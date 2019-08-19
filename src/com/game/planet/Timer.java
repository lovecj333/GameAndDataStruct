package com.game.planet;

public class Timer {

    //用于计时
    private int currentIndex;
    //计时的长度
    private int endIndex;

    public Timer(int frame){
        //传入需要计时的时间
        this.endIndex = frame;
    }

    //开始计时，让计数帧加1,如果达到设定的帧数，就返回true
    public boolean act(){
        this.currentIndex++;
        if(this.currentIndex == this.endIndex){
            //一旦到达计数时间，就把当前计数帧重置，这样就可以达到重复计时的效果了
            this.reset();
            return true;
        }
        return false;
    }

    //重置计时器
    public void reset(){
        this.currentIndex = 0;
    }
}
