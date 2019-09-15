package com.liuyubobobo.arraylistdemo;

public class Array<E> {

    private E[] data;
    private int size;

    //有参构造函数传入数组的容量capacity
    public Array(int capacity){
        data = (E[])new Object[capacity];
    }

    //无参构造函数数组容量默认为10
    public Array(){
        this(10);
    }

    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //获取数组中的元素个数
    public int getSize(){
        return size;
    }

    //返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在所有元素后添加一个新元素
    public void addLast(E e){
        add(size, e);
    }

    //在所有元素前添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }

    //在index索引位置插入一个新元素e
    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new RuntimeException("Add failed Index is out bound index = "+index);
        }
        if(size == data.length){
            resize(2 * data.length);
        }
        //index位置后的元素(包括index的元素)都向后挪动一位
        for(int i = size - 1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //查询最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    //查询第一个元素
    public E getFirst(){
        return get(0);
    }

    //获取index索引位置的元素
    public E get(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("Get failed Index is out bound index = "+index);
        }
        return data[index];
    }

    //修改index索引位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new RuntimeException("Set failed Index is out bound index = "+index);
        }
        data[index] = e;
    }

    //查找数组中是否有元素e
    public boolean contains(E e){
        return find(e) != -1;
    }

    //查找数组中元素e所在的索引查找不到返回-1
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    //从数组中删除index位置的元素 返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("Remove failed Index is out bound index = "+index);
        }
        E ret = data[index];
        //index位置后的元素都向前挪动一位
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if(size == data.length / 4 && data.length / 2 != 0){
            //当实际存放的元素数量是容量的1/4就将容量修改为原来的1/2
            //前提要保证data.length / 2为正整数
            resize(data.length / 2);
        }
        return ret;
    }

    //从数组中删除最后一个元素 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    //从数组中删除第一个元素 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    //从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    //将数组的空间容量变成newCapacity
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d \n", size, getCapacity()));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if(i != size - 1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
}
