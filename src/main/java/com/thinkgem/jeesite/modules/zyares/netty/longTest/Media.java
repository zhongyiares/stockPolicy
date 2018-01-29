package com.thinkgem.jeesite.modules.zyares.netty.longTest;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhongyi on 2018/1/29 0029.
 */
public class Media {

    public static Object execute(RequestParm request){
        String commond = request.getCommand();

       Object obj =  JSONObject.parseObject(JSONObject.toJSONString(request.getContent()));

        return obj;
    }
}
