package com.thinkgem.jeesite.modules.zyares.netty.longTest;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.zyares.netty.ClientHandlerSimple;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

/**
 * Created by zhongyi on 2018/1/22 0022.
 */
public class TcpClient {

    private static EventLoopGroup group = null;
    private static Bootstrap bootstrap = null;
    private static ChannelFuture channelFuture = null;

    static {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
//        bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,Ac);
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,Delimiters.lineDelimiter()[0]));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast( new ClientHandlerSimple());
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        try {
            channelFuture = bootstrap.connect("localhost",8090).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Object send(Request request) {

        try {

            channelFuture.channel().writeAndFlush(JSONObject.toJSONString(request));
            channelFuture.channel().writeAndFlush("\r\n");

            DefaultFuture future = new DefaultFuture(request);
            Response response = future.getResponse();

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
        return null;
    }


}
