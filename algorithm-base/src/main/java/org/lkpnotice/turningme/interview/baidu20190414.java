package org.lkpnotice.turningme.interview;

/**
 * Created by liujinpeng on 2019/4/14.
 */
public class baidu20190414 {

/*
    */
/**
     删除指定的value值d
     *//*

    public void deleteWithValue(HashMap<String,Integer> collections,Integer value){
    */
/*边界条件*//*

        if(null == collections || 0==collection.size()){
            return ;
        }
    */
/*entryset 的迭代器*//*


        Iterator<Entry<String,Integer>> iter = collection.entrySet().Iterator();
        while(iter.hasNext()){
            Entry<String,Integer> en = iter.Next();
            String k = en.getKey();
            Integer v = en.getVal();
        */
/*判断值是否相等，相等的remove掉*//*


            if( (null == value  && null == v) || (value!=null && v!=null && value == v)){
                iter.remove(en);
            }
        }

    }



    从board中找出满足条件的输入单词，条件是横竖连接。
    输入
    Given words = ["oath","pea","eat","rain"] and
    board = [
            ['o','a','a','n'],
            ['e','t','a','e'],
            ['i','h','k','r'],
            ['i','f','l','v']
            ]



    ArrayList<Integer> list,   Integer target

*/
/*
    两个思路：
    1、三层循环，枚举出所有三元组，再遍历三元组中和为target的内容 时间复杂度较高 n3
    2、排序list，然后根据排序的特性来计算
    选第二种，因为时间复杂度较低一点

*//*



    public void print3TuplesMatchTarget(List<Integer> list, Integer target){
        if(null == list || null == target){
            //异常情况
            return ;
        }

        //排序，默认升序
        Collections.sort(list);

        //计算过程，递归分析，首先选定最小的元素 ele1，遍历数组，然后确定另外两个元素


        List<Integer[]> resultSet = new Arraylist();
        for(int i=0;i<list.size();i++){
            Integer min = list.get(i);
            //计算另外两个和为 target-min的 数字组合
            innerMatch(list,min,i,list.size()-1,target,resultSet);
        }



    }


    */
/*计算 start -> end 范围内 和为target -min的两个数的组合，
    返回值在List<Integer[]> resultSet中
    *//*

    public void innerMatch(List<Integer> array, Integer min, int start,int end,Integer target,List<Integer[]> resultSet ){
        Integer bound = target -min;
        int tempStart = start;
        int tempEnd = end;
        while(tempStart > tempEnd){
            Integer tempResult = array.get(tempStart) + array.get(tempEnd);

            //找到匹配
            if(tempResult == bound){
                resultSet.add(new Integer[]{min,array.get(tempStart),array.get(tempEnd)});
                continue;
            }else if(tempResult < bound){
                tempStart++;
            }else {
                tempEnd --;
            }
        }
    }

*/







}
