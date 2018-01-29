package com.thinkgem.jeesite.modules.zyares.netty;

import io.netty.bootstrap.Bootstrap;
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
public class NettyClient2 {



    static {

    }

    public static void main(String[] args) {

        try {
            for(int i =0 ;i < 15;i++){
                new Thread(
                        new dataProcess()
                ).start();
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //group.shutdownGracefully();
        }
    }

    static class dataProcess implements Runnable{
        private  EventLoopGroup group = null;
        private  Bootstrap bootstrap = null;
        @Override
        public void run() {

            try {
                group = new NioEventLoopGroup();
                bootstrap = new Bootstrap();
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.group(group);
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
                ChannelFuture channelFuture = bootstrap.connect("localhost",8090).sync();
                String person = "张三\r\n";
                channelFuture.channel().writeAndFlush(person);
//            channelFuture.channel().writeAndFlush(Delimiters.lineDelimiter()[0]);

                channelFuture.channel().closeFuture().sync();

                Object obj = channelFuture.channel().attr(AttributeKey.valueOf("channelKey")).get();
                System.out.println(obj+", the time:"+System.currentTimeMillis());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }
        }
    }
}
