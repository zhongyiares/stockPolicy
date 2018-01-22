package com.thinkgem.jeesite.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhongyi on 2018/1/12 0012.
 */
public class CacheDemo {

    private Map<String,Object> cache = new HashMap();

    public static void main(String[] args) {

    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key){
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if(value == null){
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if(value == null){
                        value = "aaa";
                    }
                }finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        }finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}
