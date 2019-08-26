package com.nettynio.nettychat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.ArrayList;
import java.util.List;

//服务端业务处理类
public class NettyChatServerHandler extends SimpleChannelInboundHandler<String>{

    public static List<Channel> channels = new ArrayList<>();

    //通道就绪
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.add(channel);
        System.out.println("[Server:]"+channel.remoteAddress().toString().substring(1)+"上线");
    }

    //通道未就绪
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.remove(channel);
        System.out.println("[Server:]"+channel.remoteAddress().toString().substring(1)+"离线");
    }

    //读取数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        for(Channel ch : channels){
            if(ch != channel){
                ch.writeAndFlush("["+channel.remoteAddress().toString().substring(1)+"]"+"说:"+s+"\n");
            }
        }
    }
}
