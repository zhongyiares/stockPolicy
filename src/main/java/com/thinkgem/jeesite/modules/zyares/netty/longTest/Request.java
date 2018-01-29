package com.thinkgem.jeesite.modules.zyares.netty.longTest;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhongyi on 2018/1/29 0029.
 */
public class Request {

    private String command;

    private Object content;

    private final long id;

    public static final AtomicLong nid = new AtomicLong(0);

    public Request(){
        id = nid.incrementAndGet();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }
}
