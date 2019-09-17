package com.liuyubobobo.linkedlistdemo;

public class TestLinkedList {

    public static void main(String[] args) {
        LinkedListNoDummyHead<Integer> list = new LinkedListNoDummyHead<>();
        list.addFirst(100);
        list.addFirst(200);
        list.addFirst(300);
        System.out.println(list);
        list = new LinkedListNoDummyHead<>();
        list.addLast(100);
        list.addLast(200);
        list.addLast(300);
        System.out.println(list);
    }
}
