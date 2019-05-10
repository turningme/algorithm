package org.lkpnotice.turningme.structures;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by liujinpeng on 2019/5/10.
 */
public class EhCacheDemo {




    public static void main(String[] args){
        t2();

    }



    static void t1(){
        CacheManager   cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("HelloWorldCache");

        Element element = new Element("key1", "value1");

        cache.put(element);
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        cache.remove("key1");

        Dog dog = new Dog(1L, "taidi", (short)2);
        Element element2 = new Element("taidi", dog);
        cache.put(element2);
        Element value2 = cache.get("taidi");
        Dog dog2 = (Dog) value2.getObjectValue();
        System.out.println(dog2);

        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();


    }


    static void t2(){
        CacheManager   cacheManager = CacheManager.create();
        cacheManager.addCache("mycache");

        Cache cache = cacheManager.getCache("mycache");
        CacheConfiguration config = cache.getCacheConfiguration();

        config.setTimeToIdleSeconds(60);
        config.setTimeToLiveSeconds(12);
        config.setMaxEntriesInCache(10000);
        config.setMaxEntriesLocalDisk(1000000);

        Element element = new Element("key1", "value1");

        cache.put(element);
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        cache.remove("key1");

        Dog dog = new Dog(1L, "taidi", (short)2);
        Element element2 = new Element("taidi", dog);
        cache.put(element2);
        sleep(11);

        Element value2 = cache.get("taidi");
        if (null == value2){
            System.out.println("value2 is null ");
        }else{
            Dog dog2 = (Dog) value2.getObjectValue();


            System.out.println(dog2);
        }

        System.out.println(cache.getSize());



        // 7. 刷新缓存
        cache.flush();
        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }



    static void sleep(long millis){
        try {
            TimeUnit.SECONDS.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(" dd: " + e);
        }
    }




    public static class Dog implements Serializable{
        long ser;
        String name;
        int age;

        public Dog(long ser, String name, int age) {
            this.ser = ser;
            this.name = name;
            this.age = age;
        }

        public long getSer() {
            return ser;
        }

        public void setSer(long ser) {
            this.ser = ser;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "ser=" + ser +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

