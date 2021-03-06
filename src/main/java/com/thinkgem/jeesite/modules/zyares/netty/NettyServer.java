package com.thinkgem.jeesite.modules.zyares.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhongyi on 2018/1/22 0022.
 */
public class NettyServer {

    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup(3);
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_BACKLOG,128)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                            //心跳检测机制
                            ch.pipeline().addLast(new IdleStateHandler(60,20,15, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new SimpleHandler());
                            ch.pipeline().addLast(new StringEncoder());
//                            ch.config().setAllocator(PooledByteBufAllocator.DEFAULT);
//                            ch.config().setAllocator(UnpooledByteBufAllocator.DEFAULT);
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
