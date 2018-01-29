package com.thinkgem.jeesite.modules.zyares.netty.longTest;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhongyi on 2018/1/29 0029.
 */
public class TcpClientTest {

    public static void main(String[] args) {

        for(int i=0 ;i< 50 ;i++){
            Request request = new Request();
            request.setCommand("saveUser");
            User user = new User();
            user.setAge(i);
            user.setUserName("zyares");
            request.setContent(user);
            Object result =  TcpClient.send(request);
            System.out.println("客户端长连接测试返回结果:"+ JSONObject.toJSONString(result));
        }


    }
}
