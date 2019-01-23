package org.lkpnotice.turningme.comm.algorithm.other;

/**
 * Created by liujinpeng on 2019/1/23.
 *
 * Problem:Given array [1,2,3,-2,3,-1],find the sub-sequence whose multiply is the largest .
 * Solution:
 *  动态规划 最优子结构
 *  最大积子序列
 *考虑到乘积子序列中有正有负也还可能有0，我们可以把问题简化成这样：数组中找一个子序列，使得它的乘积最大；同时找一个子序列，使得它的乘积最小（负数的情况）。因为虽然我们只要一个最大积，但由于负数的存在，我们同时找这两个乘积做起来反而方便。也就是说，不但记录最大乘积，也要记录最小乘积。

 假设数组为a[]，直接利用动态规划来求解，考虑到可能存在负数的情况，我们用maxend来表示以a[i]结尾的最大连续子串的乘积值，用minend表示以a[i]结尾的最小的子串的乘积值，那么状态转移方程为：

 maxend = max(max(maxend * a[i], minend * a[i]), a[i]);
 minend = min(min(maxend * a[i], minend * a[i]), a[i]);
 */
public class MaxMultiSubSequnce {

    /**
     * construct data
     * @return
     */
    static int[] getInputData(){
        return new int[]{-1,-2,3,8,-1,-6,-9,2,3};
    }

    /**
     * test if the input is ok
     * @param input
     * @return
     */
    boolean checkLegal(int[] input){
        return  !(null == input || input.length <2);
    }



    /**
     * solve the problem
     * @param input
     */
    public void solution(int[] input){
        int minEnd = input[0];
        int maxEnd = input[0];
        int maxRes = input[0];

        for (int i = 1 ; i < input.length ; i++){
            int minEndTmp = minEnd * input[i];
            int maxEndTmp = maxEnd * input[i];

            minEnd = Math.min(input[i],Math.min(minEndTmp,maxEndTmp));
            maxEnd = Math.max(input[i],Math.max(minEndTmp,maxEndTmp));
            maxRes = Math.max(maxEnd,maxRes);
        }


        System.out.println(String.format("max values is %s",maxRes));
    }


    public static void main(String[] args){
        new MaxMultiSubSequnce().solution(getInputData());
    }



}
