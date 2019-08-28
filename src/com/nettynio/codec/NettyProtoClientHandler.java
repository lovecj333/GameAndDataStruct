package com.nettynio.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyProtoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        BookMessage.Book.Builder builder = BookMessage.Book.newBuilder();
        builder.setId(1).setName("三国演义");
        BookMessage.Book book = builder.build();
        ctx.writeAndFlush(book);
    }
}
