package com.liuyubobobo.linkedlistdemo;

public class Leetcode203 {

    public ListNode removeElements(ListNode head, int val){
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while(prev.next != null){
            if(prev.next.val == val){
                ListNode retNode = prev.next;
                prev.next = retNode.next;
                retNode.next = null;
            }else{
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Leetcode203().removeElements(head, 6);
        System.out.println(res);
    }
}
