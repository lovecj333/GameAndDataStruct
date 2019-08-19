package com.data.struct.queue;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.addQueue(10);
        queue.addQueue(20);
        queue.addQueue(30);
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        System.out.println(queue.toString());
    }
}

class ArrayQueue{

    private int maxSize;    //数组的最大容量
    private int front;      //队列头(指向队列头的前一个位置)
    private int rear;       //队列尾(指向队列尾的数据、队列中的最后一个数据)
    private int[] arr;      //用于存放数据的数组

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            throw new RuntimeException("队列满了");
        }
        rear++;
        arr[rear] = n;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front+1; i <= rear; i++) {
            if(i == rear){
                sb.append(arr[i]);
            }else{
                sb.append(arr[i]).append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
