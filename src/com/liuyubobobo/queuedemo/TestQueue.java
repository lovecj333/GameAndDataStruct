package com.liuyubobobo.queuedemo;

import java.util.Random;

public class TestQueue {

    private static double testQueue(Queue<Integer> queue, int opCount){
        long n1 = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            queue.dequeue();
        }
        long n2 = System.currentTimeMillis();
        return (n2-n1)/1000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double t1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue use : "+t1+" s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double t2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue use : "+t2+" s");
    }
}
