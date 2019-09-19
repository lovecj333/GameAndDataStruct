package com.liuyubobobo.linkedlistdemo;

//使用虚拟head节点的链表
public class LinkedList<E> {

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

    private Node dummyHead; //虚拟head节点
    private int size;

    public LinkedList(){
        dummyHead = new Node();
    }

    //获取链表中元素的个数
    public int getSize(){
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在链表的第index位置添加新的元素
    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new RuntimeException("Add failed Index is out bound index = "+index);
        }
        //找到待添加位置的前一个位置
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(e);    //新建一个node节点
        node.next = prev.next;      //node节点的next指向待添加位置的节点
        prev.next = node;           //待添加位置的前一个节点的next指向node
        size++;
    }

    //在链表头添加新的元素
    public void addFirst(E e){
        add(0, e);
    }

    //在链表尾添加新的元素
    public void addLast(E e){
        add(size, e);
    }

    //获取链表的第index位置的元素
    public E get(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("Get failed Index is out bound index = "+index);
        }
        Node cur = dummyHead.next;  //从第一个节点开始查找
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    //获取链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    //获取链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    //修改链表的第index位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new RuntimeException("Set failed Index is out bound index = "+index);
        }
        Node cur = dummyHead.next;  //从第一个节点开始查找
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    //查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //从链表中删除第index位置的元素，返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("Remove failed Index is out bound index = "+index);
        }
        //找到待删除位置节点的前一个节点
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;   //待删除的节点
        prev.next = retNode.next;   //待删除节点的前一个节点指向待删除节点的后一个节点
        retNode.next = null;
        size--;
        return retNode.e;
    }

    //从链表中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    //从链表中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    //从链表中删除元素e
    public void removeElement(E e){
        Node prev = dummyHead;
        //找到元素为e的节点的前一个节点
        while(prev.next != null){
            if(prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }
        if(prev.next != null){
            Node retNode = prev.next;
            prev.next = retNode.next;
            retNode.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
