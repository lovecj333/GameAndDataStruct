package com.liuyubobobo.queuedemo;

public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    private boolean isFull(){
        return (tail + 1) % data.length == front;
    }

    @Override
    public void enqueue(E e) {
        if(isFull()){
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = 0; i < size; i++) {
            res.append(data[(i + front) % data.length]);
            if(i != size - 1){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>(3);
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);
        queue.dequeue();
        System.out.println(queue);
    }
}
