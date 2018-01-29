package com.thinkgem.jeesite.modules.zyares.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * Created by zhongyi on 2018/1/26 0026.
 */
public class PoolBufTest {

    public static void main(String[] args) {
//        ByteBuf buf = Unpooled.buffer();
//        buf.writeByte("helloworld".getBytes());

        ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer(1024);
        buf.writeBytes("helloworld".getBytes());
        buf.release();

//        for()

    }
}
