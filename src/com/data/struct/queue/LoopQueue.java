package com.data.struct.queue;

public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
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

    @Override
    public boolean isFull() {
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
            throw new RuntimeException("Queue is Empty");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new RuntimeException("Queue is Empty");
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
        res.append("front [");
        for (int i = 0; i < size; i++) {
            if(i == size - 1){
                res.append(data[(i + front) % data.length]);
            }else{
                res.append(data[(i + front) % data.length]).append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 0){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
