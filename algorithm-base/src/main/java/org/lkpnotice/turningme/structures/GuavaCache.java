package org.lkpnotice.turningme.structures;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liujinpeng on 2019/5/10.
 */
public class GuavaCache {
    private static LoadingCache<Integer, People> cache = CacheBuilder
            .newBuilder()
            // 缓存容量
            .maximumSize(2)
            // 缓存超时时间
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build(new CacheLoader<Integer, People>() {
                // 缓存加载数据方式
                public People load(Integer id) throws Exception {
                    System.out.println("cache load " + id);
                    return new People(id, "people_" + id);
                }
            });

    static  class People{
        private Integer id;
        private String name;

        public People(Integer id, String name){
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "People{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }



    static void test1() throws Exception {
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        // 由于缓存容量为2，因此会将key=1的数据从缓存移除，将key=3的数据插入缓存
        System.out.println(cache.get(3));
        System.out.println();

        List<Integer> keys = new LinkedList<Integer>();
        keys.add(1);
        keys.add(2);
        keys.add(3);
        // 查询所有缓存中的数据
        Map<Integer, People> map = cache.getAllPresent(keys);
        for(Integer key : map.keySet()){
            System.out.println(map.get(key));
        }
        System.out.println();

        TimeUnit.SECONDS.sleep(10);
        // 由于超时，缓存中的数据已被移除
        System.out.println(cache.getIfPresent(2));
        System.out.println(cache.getIfPresent(3));
        System.out.println();

        // 更新缓存的中数据
        cache.refresh(1);
    }




    static void test2(){
        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10)  //最多存放十个数据
                .expireAfterWrite(10, TimeUnit.SECONDS)  //缓存200秒
                .recordStats()   //开启 记录状态数据功能
                .build(new CacheLoader<String, Integer>() {
                    //数据加载，默认返回-1,也可以是查询操作，如从DB查询
                    @Override
                    public Integer load(String key) throws Exception {
                        return -1;
                    }
                });

        //只查询缓存，没有命中，即返回null。 miss++
        System.out.println(cache.getIfPresent("key1")); //null
        //put数据，放在缓存中
        cache.put("key1", 1);

        //再次查询，已存在缓存中, hit++
        System.out.println(cache.getIfPresent("key1")); //1
        //失效缓存
        cache.invalidate("key1");

        //失效之后，查询，已不在缓存中, miss++
        System.out.println(cache.getIfPresent("key1")); //null

        try{
            //查询缓存，未命中，调用load方法，返回-1. miss++
            System.out.println(cache.get("key2"));   //-1
            //put数据，更新缓存
            cache.put("key2", 2);
            //查询得到最新的数据, hit++
            System.out.println(cache.get("key2"));    //2
            System.out.println("size :" + cache.size());  //1

            //插入十个数据
            for(int i=3; i<13; i++){
                cache.put("key"+i, i);
            }
            //超过最大容量的，删除最早插入的数据，size正确
            System.out.println("size :" + cache.size());  //10
            //miss++
            System.out.println(cache.getIfPresent("key2"));  //null

            Thread.sleep(5000); //等待5秒
            cache.put("key1", 1);
            cache.put("key2", 2);
            //key5还没有失效，返回5。缓存中数据为key1，key2，key5-key12. hit++
            System.out.println(cache.getIfPresent("key5")); //5

            Thread.sleep(5000); //等待5秒
            //此时key5-key12已经失效，但是size没有更新
            System.out.println("size :" + cache.size());  //10
            //key1存在, hit++
            System.out.println(cache.getIfPresent("key1")); //1
            System.out.println("size :" + cache.size());  //10
            //获取key5，发现已经失效，然后刷新缓存，遍历数据，去掉失效的所有数据, miss++
            System.out.println(cache.getIfPresent("key5")); //null
            //此时只有key1，key2没有失效
            System.out.println("size :" + cache.size()); //2

            System.out.println("status, hitCount:" + cache.stats().hitCount()
                    + ", missCount:" + cache.stats().missCount()); //4,5
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }





    public static void main(String[] args) throws Exception{
        test2();
    }



}
