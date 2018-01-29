package com.thinkgem.jeesite.modules.zyares.netty;


import io.netty.channel.Channel;

/**
 * Created by zhongyi on 2018/1/24 0024.
 */
public class MyThread extends Thread {

    private Channel channel;

    private Object msg;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        channel.writeAndFlush(msg);
    }
}
