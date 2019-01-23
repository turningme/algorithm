package org.lkpnotice.turningme.comm.algorithm.other;

/**
 * Created by liujinpeng on 2019/1/23.
 *
 * Problem: given an array [1,2,-1,4,5,-3] ,find the sub-sequence whose sum is the biggest
 *
 * Solution:
 *  local context global context
 *  cursor
 *  if i->j is maximum then 0->i <0
 *
 *  动态规划
 *  最优子结构
 *
 *
 */
public class MaxSumSubSequnce {
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
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int localStart=0;
        int localEnd = 0;
        int localGain = input[0];

        int globalStart = localStart;
        int globalEnd = localEnd;
        int globalGain = localGain;

        for (int i = 1 ; i < input.length ; i ++){
            //bottom found, update context-local vars
            if ( localGain <0){

                localStart = localEnd = i;
                localGain = input[localStart];

            }else {//more gains found
                localEnd = i;
                localGain = localGain + input[localEnd];

                if (localGain > globalGain){
                    globalStart = localStart;
                    globalEnd = localEnd;
                    globalGain = localGain;
                }
            }

        }


        if (localGain > globalGain){
            globalStart = localStart;
            globalEnd = localEnd;
            globalGain = localGain;
        }

        System.out.println( String.format("%s,%s,%s",globalStart,globalEnd,globalGain));

    }


    /**
     *
     * @param args
     */
    public static void main(String[] args){
        new MaxSumSubSequnce().solution(getInputData());
    }
}
