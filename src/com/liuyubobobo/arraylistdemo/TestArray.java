package com.liuyubobobo.arraylistdemo;

public class TestArray {

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i+1);
        }
        System.out.println(arr);
        arr.addFirst(100);
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
        arr.addFirst(200);
        System.out.println(arr);
    }
}
