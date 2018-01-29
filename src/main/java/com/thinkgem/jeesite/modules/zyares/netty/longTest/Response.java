package com.thinkgem.jeesite.modules.zyares.netty.longTest;

/**
 * Created by zhongyi on 2018/1/29 0029.
 */
public class Response {

    private long id;

    private Object content;

    private int status; //响应码

    private String msg ; //响应信息

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
