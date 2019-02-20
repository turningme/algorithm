package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liujinpeng on 2019/2/19.
 * Problem: given two linked lists A and B which are sorted  monotone increasing,merge this two list with requirement that no descending..
 *
 * Solution: classic problem in linked list operation.
 * 两个同顺序链表的merge 一个排序链表
 */
public class No16MergeTwoLinkedListSorted {


    /**
     * 产生两个单调递增的输入序列
     * @return
     */
    static List<Integer> linkedListDataA(){
        List<Integer> data = new LinkedList<Integer>();
        for (int i = 0;i<10;i++){
            data.add(i);
        }
        return data;
    }


    static List<Integer> linkedListDataB(){
        List<Integer> data = new LinkedList<Integer>();
        for (int i = 3;i<12;i++){
            data.add(i);
        }
        return data;
    }

    /**
     * 新创建一个result 链表，从两个输入链表按照算法遍历
     * @param inputA
     * @param inputB
     * @return
     */
    List<Integer> solution(List<Integer> inputA,List<Integer> inputB){
        if (null == inputA){
            inputA = Collections.emptyList();
        }

        if (null == inputB){
            inputB = Collections.emptyList();
        }

        List<Integer> result = new ArrayList<Integer>();

        //循环条件是两个链表都不是空，循环变量就是两个表的长度变化
        while(!inputA.isEmpty() && !inputB.isEmpty()){
            Integer aData = inputA.get(0);
            Integer bData = inputB.get(0);
            if (aData <= bData){
                result.add(aData);
                inputA.remove(0);
            }else{
                result.add(bData);
                inputB.remove(0);
            }
        }

        //退出循环 表明必然有一个链表非空，一个为空，所以只要将不是空链表剩余部分挂在后边就可以了
        if (!inputA.isEmpty()){
            result.addAll(inputA);
        }

        if (!inputB.isEmpty()){
            result.addAll(inputB);
        }


        return result;
    }


    static void printlnList(List<Integer> input){
        if (null == input){
            System.out.println("list is null");
        }

        StringBuffer sbuffer = new StringBuffer("list :");
        for (Integer e:input
             ) {
            sbuffer.append(String.format(" %s ",e));
        }
        System.out.println(sbuffer.toString());
    }


    public static void main(String[] args){
        List<Integer> inputA = linkedListDataA();
        List<Integer> inputB = linkedListDataB();
        printlnList(inputA);
        printlnList(inputB);
        No16MergeTwoLinkedListSorted instance = new No16MergeTwoLinkedListSorted();
        List<Integer> result =  instance.solution(inputA,inputB);
        printlnList(result);

    }


}
