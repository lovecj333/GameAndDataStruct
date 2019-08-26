package com.nettynio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) throws Exception{
        //创建一个线程组: 处理客户端网络操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建客户端启动助手来配置参数
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)   //设置线程组
                 .channel(NioSocketChannel.class)  //使用NioSocketChannel作为客户端通道的实现
                 .handler(new ChannelInitializer<SocketChannel>() { //创建通道初始化对象
                     @Override  //pipeline链中添加自定义的handler类
                     protected void initChannel(SocketChannel socketChannel) throws Exception {
                         ChannelPipeline pipeline = socketChannel.pipeline();
                         pipeline.addLast(new NettyClientHandler());
                     }
                 });
        System.out.println("..........Client is ready..........");
        ChannelFuture cf = bootstrap.connect("127.0.0.1", 9999).sync();//连接服务端
        //关闭通道、关闭线程组
        cf.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }
}
