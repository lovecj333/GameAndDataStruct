package com.nettynio.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class NettyProtoClient {

    public static void main(String[] args) throws Exception{
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("encoder", new ProtobufEncoder());
                        pipeline.addLast(new NettyProtoClientHandler());
                    }
                });
        System.out.println("..........Client is ready..........");
        ChannelFuture cf = bootstrap.connect("127.0.0.1", 9999).sync();//连接服务端
        //关闭通道、关闭线程组
        cf.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }
}