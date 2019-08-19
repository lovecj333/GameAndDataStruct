package com.data.struct.linked;

public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.add(new Node(1));
        linkedList.add(new Node(2));
        linkedList.add(new Node(3));
        linkedList.add(new Node(4));
        linkedList.add(new Node(5));
        linkedList.list();
        System.out.println("-----------------------");
        linkedList.josepfuPrint(1, 2);
    }
}

class CircleSingleLinkedList{   //单向环形链表

    private Node first = null; //头节点
    private Node curNode = null;  //当前指向的节点(辅助节点)
    private int size;

    public void add(Node node){
        if(first == null){
            first = node;
            first.next = first;
            curNode = first;
        }else{
            curNode.next = node;
            node.next = first;
            curNode = node;
        }
        size++;
    }

    public void list(){
        if(first == null){
            System.out.println("linkedlist is empty");
            return;
        }
        Node node = first;
        while(true){
            System.out.println(node.no);
            if(node.next == first){
                break;
            }
            node = node.next;
        }
    }

    //输出节点出链表顺序 startNo开始的编号 interval第几个出链表
    public void josepfuPrint(int startNo, int interval){
        if(first == null || startNo < 1 || startNo > size){
            System.out.println("param is error");
            return;
        }
        //得到最后一个节点
        Node tail = first;
        while(true){
            if(tail.next == first){
                break;
            }
            tail = tail.next;
        }
        //报数之前、先让first和tail移动到指定的位置、移动 startNo-1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            tail = tail.next;
        }
        //报数时、让first和tail移动 interval-1 次 first出链表
        //循环操作、直到链表中只有一个节点
        while(true){
            if(tail == first){
                break;
            }
            for (int i = 0; i < interval-1; i++) {
                first = first.next;
                tail = tail.next;
            }
            //first指向的节点就是要出链表的节点
            System.out.println("出链表节点: "+first.no);
            first = first.next;
            tail.next = first;
        }
        System.out.println("链表中剩余的节点为: "+first.no);
    }
}

class Node{

    public int no;
    public Node next;

    public Node(int no){
        this.no = no;
    }
}