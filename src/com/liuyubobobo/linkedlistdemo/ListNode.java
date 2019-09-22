package com.liuyubobobo.linkedlistdemo;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val){
        this.val = val;
    }

    //使用arr为参数，创建一个链表，当前的ListNode为链表头结点
    public ListNode(int[] arr){
        if(arr == null || arr.length == 0){
            throw new RuntimeException("arr can not be empty");
        }
        ListNode cur = this;
        cur.val = arr[0];
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    //以当前节点为头结点的链表信息字符串
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            res.append(cur.val).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
