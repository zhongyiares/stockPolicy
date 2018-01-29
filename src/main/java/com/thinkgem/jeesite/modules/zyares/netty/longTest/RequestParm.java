package com.thinkgem.jeesite.modules.zyares.netty.longTest;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhongyi on 2018/1/29 0029.
 */
public class RequestParm {

    private String command;

    private Object content;

    private long id;

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

    public void setId(long id) {
        this.id = id;
    }
}
