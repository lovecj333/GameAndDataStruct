package com.nettynio;

public class Test {

    public static int flag = 0;

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while(true){
            synchronized (Double.class){
                if(flag > 0){
                    System.out.println(flag);
                }
            }
        }
    }
}

class ThreadDemo implements Runnable{

    //private volatile boolean flag = false;
    //private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Test.flag++;
        System.out.println("flag="+Test.flag);
    }

//    public boolean isFlag() {
//        return flag;
//    }
}