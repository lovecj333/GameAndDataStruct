package com.liuyubobobo.recursiondemo;

import com.liuyubobobo.linkedlistdemo.Leetcode203;
import com.liuyubobobo.linkedlistdemo.ListNode;

public class Leetcode203R {

    public ListNode removeElements(ListNode head, int val){
        if(head == null){
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Leetcode203().removeElements(head, 6);
        System.out.println(res);
    }
}
