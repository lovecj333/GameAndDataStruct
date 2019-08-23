package com.nettynio.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class NettyServer {

    public static void main(String[] args) {
        //创建一个线程组：接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建一个线程组：处理客户端网络操作
    }
}
