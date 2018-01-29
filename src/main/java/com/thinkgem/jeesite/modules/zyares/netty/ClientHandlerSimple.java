package com.thinkgem.jeesite.modules.zyares.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

/**
 * Created by zhongyi on 2018/1/22 0022.
 */
public class ClientHandlerSimple extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("客户端开始读取数据");
//        System.out.println("接受服务器的数据为:"+msg.toString());

        if(msg.toString().equals("ping")){
            ctx.channel().writeAndFlush("ping\r\n");
            return;
        }

        ctx.channel().attr(AttributeKey.valueOf("channelKey")).set(msg.toString());
        System.out.println("接受服务器的数据为:"+msg.toString());
        ctx.channel().close();
    }
}
