package com.data.struct.queue;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);//给定的空间是4、实际的空间是3、预留了一个位置、方便计算
        queue.addQueue(10);
        queue.addQueue(20);
        queue.addQueue(30);
        System.out.println(queue.getQueue());
        System.out.println(queue.toString());
        queue.addQueue(100);
        System.out.println(queue.toString());
        System.out.println(queue.getQueue());
        System.out.println(queue.toString());
    }
}

class CircleArrayQueue{

    private int maxSize;    //数组的最大容量
    private int front;      //队列头(指向队列的第一个元素的位置、初始值为0)
    private int rear;       //队列尾(指向队列总元素的个数+1的位置、初始值为0)
    private int[] arr;      //用于存放数据的数组

    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            throw new RuntimeException("队列满了");
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear向后移动、这里必须考虑取模、大于数组的长度就到第一个位置
        rear = (rear + 1) % maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int value = arr[front];
        //将front向后移动、考虑取模
        front = (front + 1) % maxSize;
        return value;
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

    //队列中有效元素的公式 (rear + maxSize - front) % maxSize
    public int size(){
        // rear = 1 front = 0 maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front; i < front + this.size(); i++) {
            sb.append(arr[i % maxSize]).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
