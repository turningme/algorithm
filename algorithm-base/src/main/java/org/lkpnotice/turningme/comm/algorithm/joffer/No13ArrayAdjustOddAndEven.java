package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.Arrays;

/**
 * Created by liujinpeng on 2019/2/2.
 *
 *
 * Problem:given an array of integer number with odd and even ,ensure all odds are ahead of all evens and  the relative order no  changed with in odds
 * and evens respected..
 *
 * Solution:
 * 存储空间就是一个数组，每一次先找到偶数第一个，然后找到它后边的第一个基数，直到寻找的奇数超出边界
 * 增加两个线性表，扫一次数组，分别分拣出奇数和偶数到两个队列，然后在放回去
 * 归并排序
 * 快排要变种
 *
 *
 *
 *
 */
public class No13ArrayAdjustOddAndEven {

    static int[] getData(){
        return new int[]{1,2,3,4,5,6,7,8,10,3,7,11};
    }



    int[] solutionSimple(int[] input){
        if (null == input ||input.length ==0){
            return input;
        }



        int firstEvenPos = 0;
        int firstOddPosAfterEven = 0;

        while(firstEvenPos<input.length){
            while(firstEvenPos<input.length && input[firstEvenPos]%2==1 ){
                firstEvenPos++;
            }

            if (firstEvenPos ==input.length)
                return input;
            //get the first even pos
            firstOddPosAfterEven = firstEvenPos+1;
            while(firstOddPosAfterEven<input.length &&input[firstOddPosAfterEven]%2==0 ){
                firstOddPosAfterEven++;
            }

            //all evens after firstEvenPos
            if (firstOddPosAfterEven ==input.length)
                return input;


            //put firstOddPosAfterEven to the fist of input{firstEvenPos-firstOddPosAfterEven}
            passLast2First(input,firstEvenPos,firstOddPosAfterEven);

            //found evens fall back 1 step
            /*firstEvenPos += 1;
            firstOddPosAfterEven += 1;*/

        }


        return input;
    }


    /**
     *
     *
     * @param data
     * @param start
     * @param end
     */
    static void passLast2First(int[] data,int start, int end){
        int temp = data[end];
        for (int i=end;i>start;i--){
            data[i] = data[i-1];
        }
        data[start] = temp;
    }



    static String getString(int[] input){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0; i<input.length ;i++){
            stringBuffer.append(input[i]).append("  ");
        }
        return  stringBuffer.toString();
    }



    void mergeSort(int[] input,int start,int end){
        if (start ==end){
            return ;
        }



    }


    public static void main(String[] args){
        int[] input =   getData();
        No13ArrayAdjustOddAndEven instance = new No13ArrayAdjustOddAndEven();
        System.out.println(String.format("original array %s", getString(input)));
        System.out.println(String.format("simple solution %s", getString(instance.solutionSimple(input))));

    }
}
