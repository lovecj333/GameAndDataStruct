package com.nettynio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//通过NIO实现文件IO
public class TestFileNio {

    //往本地文件中写数据
    public void test1() throws Exception {
        //创建输出流
        FileOutputStream fos = new FileOutputStream("basic.txt");
        //从流中得到一个通道
        FileChannel fc = fos.getChannel();
        //提供一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //往缓冲区中存入数据
        String str = "hello nio";
        buffer.put(str.getBytes());
        //翻转缓冲区
        buffer.flip();
        //把缓冲区的数据写到通道中(通道会负责把数据写到文件中)
        fc.write(buffer);
        //关闭(通道是由Stream获取的所以只需要关闭Stream对象)
        fos.close();
    }

    //从本地文件中读取数据
    public void test2() throws Exception {
        File file = new File("basic.txt");
        //创建输入流
        FileInputStream fis = new FileInputStream(file);
        //从流中得到一个通道
        FileChannel fc = fis.getChannel();
        //提供一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
        //通道读取数据存入缓冲区
        fc.read(buffer);
        System.out.println(new String(buffer.array()));
        //关闭(通道是由Stream获取的所以只需要关闭Stream对象)
        fis.close();
    }

    //使用NIO实现文件复制
    public void test3() throws Exception {
        //创建两个流
        FileInputStream fis = new FileInputStream("basic.txt");
        FileOutputStream fos = new FileOutputStream("F:\\hello.txt");
        //得到两个通道
        FileChannel sourceChannel = fis.getChannel();
        FileChannel destChannel = fos.getChannel();
        //复制(从source复制到dest)
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        //关闭
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws Exception {
        TestFileNio t = new TestFileNio();
        //t.test1();
        //t.test2();
        t.test3();
    }
}
