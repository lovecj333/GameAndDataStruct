package com.liuyubobobo.linkedlistdemo;

//没有使用虚拟head节点的链表
public class LinkedListNoDummyHead<E> {

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

    private Node head;  //这里的head节点就是第一个节点
    private int size;

    public LinkedListNoDummyHead(){

    }

    //获取链表中的元素个数
    public int getSize(){
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在链表头添加新的元素e
    public void addFirst(E e){
        Node node = new Node(e);//新建一个节点node
        node.next = head;//node的next指向head节点这样node就成为第一个节点
        head = node;//再让head指向第一个节点
        //head = new Node(e, head);//还有一个简便的写法
        size++;
    }

    //在链表的index位置添加新的元素
    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new RuntimeException("Add failed Index is out bound index = "+index);
        }
        if(index == 0){
            addFirst(e);
        }else{
            //找到待添加位置的前一个位置的节点
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Node node = new Node(e);//新建一个节点node
            node.next = prev.next;//node的next指向prev节点的next节点
            prev.next = node;//prev.next指向node这样node就在prev节点的后面
            //prev.next = new Node(e, prev.next);//还有一个简便的写法
            size++;
        }
    }

    //在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
