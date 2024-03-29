package com.liuyubobobo.queuedemo;

public class LinkedListQueue<E> implements Queue<E>{

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue(){

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail == null){   //队列为空的情况
            tail = new Node(e);
            head = tail;
        }else{  //队列不为空的情况
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if(head == null){
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front");
        Node cur = head;
        while(cur != null){
            res.append(cur+"->");
            cur = cur.next;
        }
        res.append("Null tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
    }
}
