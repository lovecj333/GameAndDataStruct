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

        System.out.println("**********************************");

        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list2.addFirst(i);
            System.out.println(list2);
        }
        list2.add(2, 666);
        System.out.println(list2);
        list2.remove(2);
        System.out.println(list2);
        list2.removeFirst();
        System.out.println(list2);
        list2.removeLast();
        System.out.println(list2);
    }
}
