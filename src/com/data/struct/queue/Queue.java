package com.data.struct.queue;

public interface Queue<E> {

    public int getSize();

    public boolean isEmpty();

    public boolean isFull();

    public void enqueue(E e);

    public E dequeue();

    public E getFront();
}
