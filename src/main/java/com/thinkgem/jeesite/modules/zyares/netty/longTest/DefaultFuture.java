package com.thinkgem.jeesite.modules.zyares.netty.longTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhongyi on 2018/1/29 0029.
 */
public class DefaultFuture {

    private long id;

    private volatile Response response = new Response();

    public final static Map<Long,DefaultFuture>  FUTURES = new ConcurrentHashMap<Long, DefaultFuture>();

    private volatile Lock lock = new ReentrantLock();

    private volatile Condition condition = lock.newCondition();

    public DefaultFuture() {
    }

    public DefaultFuture(Request request) {
        id = request.getId();
        response.setId(id);
        response.setContent(request.getContent());
        FUTURES.put(id,this);
    }

    public long getId() {
        return id;
    }


    public Response getResponse() {

        lock.lock();
        while (!hasDone()){
            try {
                condition.wait();
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

            }
        }


        return response;
    }

    private boolean hasDone() {
        return response != null ? true : false;
    }

    /**
     * 收到服务器响应
     * @param response
     */
    public static void revice(Response response){
        //找到response相对应的defauldfutre
        DefaultFuture future  = FUTURES.remove(response.getId());
        if(future == null){
            return ;
        }
        Lock lock = future.getLock();
        lock.lock();
        try {
            future.setResponse(response);
            Condition condition = future.getCondition();
            if(condition != null){
                condition.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
