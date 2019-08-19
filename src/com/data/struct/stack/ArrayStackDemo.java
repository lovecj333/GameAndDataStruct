package com.data.struct.stack;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.list();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.list();
    }
}

class ArrayStack{

    private int maxSize;    //栈的大小
    private int[] data;
    private int top = -1;   //栈顶元素位置、初始化为-1

    public ArrayStack(int capacity){
        this.maxSize = capacity;
        this.data = new int[capacity];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if(isFull()){
            throw new RuntimeException("stack is full");
        }
        top++;
        data[top] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        int value = data[top];
        data[top] = 0;
        top--;
        return value;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("stack is empty");
            return;
        }
        //从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.println("data["+i+"] = " + data[i]);
        }
    }
}