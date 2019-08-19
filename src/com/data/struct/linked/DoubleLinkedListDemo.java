package com.data.struct.linked;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList linkedList = new DoubleLinkedList();
//        linkedList.add(hero1);
//        linkedList.add(hero2);
//        linkedList.add(hero3);
//        linkedList.add(hero4);
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero2);
        linkedList.list();

        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        linkedList.update(newHeroNode);
        System.out.println("修改后的链表");
        linkedList.list();

        linkedList.del(4);
        System.out.println("删除后的链表");
        linkedList.list();
    }
}

class DoubleLinkedList{

    //初始化一个head节点, head节点不要动, 不存放具体的数据
    HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead(){
        return head;
    }

    //在尾部添加
    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while(temp.next != null){//找到最后一个节点
            temp  = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按照指定的顺序添加
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            if(heroNode.next != null){
                heroNode.next.pre = heroNode;
            }
        }
    }

    public void update(HeroNode2 heroNode){
        if(isEmpty()){
            System.out.println("linked is empty");
            return;
        }
        HeroNode2 temp = head;
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
        HeroNode2 temp = head;
        boolean isExist = false;
        while(temp.next != null){   //找到要删除的节点
            temp = temp.next;
            if(temp.no == no){
                isExist = true;
                break;
            }
        }
        if(isExist){
            temp.pre.next = temp.next;  //要删除的节点的上一个节点的next指向要删除节点的下一个节点
            if(temp.next != null){
                //要删除的节点不是最后一个节点
                temp.next.pre = temp.pre;   //要删除节点的下一个节点的pre指向要删除节点的上一个节点
            }
        }else{
            System.out.println("没有这个ID的节点 no = " + no);
        }
    }

    public void list() {
        if(isEmpty()){
            System.out.println("linked is empty");
            return;
        }
        HeroNode2 temp = head;
        while(temp.next != null){
            temp = temp.next;
            System.out.println(temp);
        }
    }

    public boolean isEmpty(){
        return head.next == null;
    }
}

class HeroNode2{

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;  //指向下一个节点
    public HeroNode2 pre;   //指向上一个节点

    public HeroNode2(int no, String name, String nickName) {
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
