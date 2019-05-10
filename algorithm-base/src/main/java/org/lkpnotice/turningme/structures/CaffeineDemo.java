package org.lkpnotice.turningme.structures;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * Created by liujinpeng on 2019/5/10.
 */
public class CaffeineDemo {
    public static void main(String[] args){
        test();
    }


    static void test(){
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(13, TimeUnit.SECONDS)
                .expireAfterAccess(3,TimeUnit.SECONDS)
                .maximumSize(10)
                .build();
        cache.put("hello","hello");


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cache.getIfPresent("hello"));
    }
}
