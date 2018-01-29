package com.thinkgem.jeesite.modules.zyares.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;

/**
 * Created by zhongyi on 2018/1/22 0022.
 */
public class SimpleHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        System.out.println("开始读取数据.....");
        if(msg instanceof ByteBuf){
            ByteBuf req = (ByteBuf)msg;
            String content =  req.toString(Charset.defaultCharset());
            System.out.println(content);
            MyThread thread = new MyThread();
            thread.setChannel(ctx.channel());
            thread.setMsg("this is test response \r\n");
            thread.start();
            ctx.channel().writeAndFlush("李四\r\n");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.equals(IdleState.READER_IDLE)){
                System.out.println("读空闲=====>");
                ctx.close();
            }else if(event.equals(IdleState.WRITER_IDLE)){
                System.out.println("写空闲=====>");
            }else if(event.equals(IdleState.ALL_IDLE)){
                System.out.println("读写空闲=====>");
                ctx.channel().writeAndFlush("ping\r\n");
            }
        }
    }
}
