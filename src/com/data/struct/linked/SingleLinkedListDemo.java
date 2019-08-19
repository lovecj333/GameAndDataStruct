package com.data.struct.linked;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList linkedList = new SingleLinkedList();

        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero3);
        linkedList.add(hero4);

//        linkedList.addByOrder(hero1);
//        linkedList.addByOrder(hero4);
//        linkedList.addByOrder(hero2);
//        linkedList.addByOrder(hero3);

//        HeroNode newHeroNode = new HeroNode(2, "呵呵呵", "玉麒麟~~");
//        linkedList.update(newHeroNode);

//        linkedList.del(1);
//        linkedList.del(4);
//        linkedList.del(2);

        linkedList.list();
        reversePrint(linkedList.getHead());

//        System.out.println("单链表反转方法");
//        reverseLinkedList(linkedList.getHead());
//        linkedList.list();

//        System.out.println("有效节点的个数: " + getLength(linkedList.getHead()));

//        HeroNode res = findLastIndexNode(linkedList.getHead(), 2);
//        System.out.println(res);
    }

    //将单链表倒序打印、使用栈来实现
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return; //空链表，不能打印
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    //将单链表反转
    public static void reverseLinkedList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode cur = head.next;
        HeroNode next = null;
        while(cur != null){     //此循环的作用是将原来链表的节点依次的放在reverseHead的后面
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第k个结点
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        //判断如果链表为空，返回null
        if(head.next == null){
            return null;
        }
        //得到链表的有效节点个数
        int length = getLength(head);
        if(index <= 0 || index > length){
            return null;
        }
        //(length - index)位置就是倒数第index个节点
        HeroNode temp = head.next;//3 // 3 - 1 = 2
        for (int i = 0; i < (length - index); i++) {
            temp = temp.next;
        }
        return temp;
    }

    //根据单链表的头节点得到单链表中有效节点的个数(有效节点不包括头节点)
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }
}

class SingleLinkedList{

    //初始化一个head节点, head节点不要动, 不存放具体的数据
    HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead(){
        return head;
    }

    //在尾部添加
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while(temp.next != null){//找到最后一个节点
            temp  = temp.next;
        }
        temp.next = heroNode;
    }

    //按照指定的顺序添加
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean isExist = false;
        //找到要添加的节点的前一个结点
        while(temp.next != null){
            if(temp.next.no == heroNode.no){//编号已存在
                isExist = true;
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到
                break;
            }
            temp = temp.next;
        }
        if(isExist){
            System.out.println("编号已存在、添加失败 no = " + heroNode.no);
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode heroNode){
        if(isEmpty()){
            System.out.println("linked is empty");
            return;
        }
        HeroNode temp = head;
        boolean isExist = false;
        while(temp.next != null){
            temp = temp.next;
            if(temp.no == heroNode.no){
                isExist = true;
                break;
            }
        }
        if(isExist){
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else{
            System.out.println("没有这个ID的节点 no = " + heroNode.no);
        }
    }

    public void del(int no){
        if(isEmpty()){
            System.out.println("linked is empty");
            return;
        }
        HeroNode temp = head;
        boolean isExist = false;
        while(temp.next != null){   //找到要删除节点的前一个节点
            if(temp.next.no == no){
                isExist = true;
                break;
            }
            temp = temp.next;
        }
        if(isExist){
            temp.next = temp.next.next;
        }else{
            System.out.println("没有这个ID的节点 no = " + no);
        }
    }

    public void list() {
        if(isEmpty()){
            System.out.println("linked is empty");
            return;
        }
        HeroNode temp = head;
        while(temp.next != null){
            temp = temp.next;
            System.out.println(temp);
        }
    }

    public boolean isEmpty(){
        return head.next == null;
    }
}

class HeroNode{

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;   //指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}