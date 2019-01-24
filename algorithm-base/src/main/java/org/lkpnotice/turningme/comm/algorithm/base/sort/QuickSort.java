package org.lkpnotice.turningme.comm.algorithm.base.sort;

import java.util.Arrays;

/**
 * Created by liujinpeng on 2019/1/24.
 *
 * Problem : given an array A, sort its elements .
 * Solution: quick sort ,average complexity is O(nlgn)
 *
 * find the split point and do the same sort procedure with left partition and right partition
 *
 * 问题分析，形成严谨的逻辑体系
 *
 */
public class QuickSort {

    static int[] getInputData(){
        return new int[]{20,20,40,50,10,15,16,60,20};
    }



    void solution(int[] input){
        if (null == input || input.length <2){
            System.out.println("Data is not legal or  need not to sort .");
            return;

        }
        printArray(input);
        sortOnce(input,0,input.length-1);
        printArray(input);
    }


    void printArray(int[] input){
        for (int i = 0 ; i < input.length ;i ++)
            System.out.println(input[i]);
    }

    /**
     * quick sort bypass once
     * @param input
     * @param start
     * @param end
     */
    void sortOnce(int[] input, int start , int end){
        if (null == input || start >= end){
            return;
        }

        int tmpStart = start;
        int tmpEnd = end;
        int tmp = input[tmpStart];

        while(tmpStart < tmpEnd){

            while(tmpStart < tmpEnd && input[tmpEnd] >= tmp){
                tmpEnd--;
            }
            if (tmpStart < tmpEnd){
                input[tmpStart] = input[tmpEnd];
            }


            while(tmpStart < tmpEnd && input[tmpStart] <= tmp){
                tmpStart++;
            }
            if (tmpStart < tmpEnd){
                input[tmpEnd] = input[tmpStart];
            }
        }

        input[tmpStart] = tmp;

        sortOnce(input,start,tmpEnd-1);
        sortOnce(input,tmpStart+1,end);
    }



    public static void main(String[] args){
        new QuickSort().solution(getInputData());
    }
}
