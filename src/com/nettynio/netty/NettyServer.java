package com.nettynio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws Exception{
        //创建一个线程组：接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建一个线程组：处理客户端网络操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建服务端启动助手来配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)   //设置两个线程组
                       .channel(NioServerSocketChannel.class)  //使用NioServerSocketChannel作为服务端通道的实现
                       .option(ChannelOption.SO_BACKLOG, 128)  //设置线程队列中等待连接的个数
                       .childOption(ChannelOption.SO_KEEPALIVE, true)  //保持活动连接的状态
                       .childHandler(new ChannelInitializer<SocketChannel>() {  //创建通道初始化对象
                           @Override    //pipeline链中添加自定义的handler类
                           protected void initChannel(SocketChannel socketChannel) throws Exception {
                               ChannelPipeline pipeline = socketChannel.pipeline();
                               pipeline.addLast(new NettyServerHandler());
                           }
                       });
        System.out.println("..........Server is ready..........");
        ChannelFuture cf = serverBootstrap.bind(9999).sync();  //绑定端口 sync()方法等待绑定的结果
        System.out.println("..........Server is start..........");
        //关闭通道、关闭线程组
        cf.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
