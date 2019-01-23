package org.lkpnotice.turningme.comm.algorithm.other;

/**
 * Created by liujinpeng on 2019/1/22.
 *
 * Problem : given an array , like the varying trend of stock price [1,3,5,1,2,10],find the buy point and sell point
 * so that array[sell]-array[buy] is maximum.
 *
 * Solution:   complexity  O(1) O(n) O(lgn) O(nlgn) O(n2)
 *
 * we always forget the easiest & detailed thing ,which is vital
 */
public class TrendBuyAndSell {


    /**
     * construct data
     * @return
     */
    static int[] getInputData(){
        return new int[]{9,8,-1,-6,-9};
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
        int localGain = 0;

        int globalStart = localStart;
        int globalEnd = localEnd;
        int globalGain = localGain;

        for (int i = 1 ; i < input.length ; i ++){
            //bottom found, update context-local vars
            if (input[localStart] > input[i]){
                globalStart = localStart;
                globalEnd = localEnd;
                globalGain = localGain;

                localStart = localEnd = i;
                localGain = 0;

            }else if (input[i] - input[localStart] > localGain){//more gains found
                localEnd = i;
                localGain = input[localEnd] - input[localStart];
            }

        }

        //test if local context preferred
        if (localGain > globalGain){
            globalStart = localStart;
            globalEnd = localEnd;
            globalGain = localGain;
        }


        System.out.println(globalGain <=0 ? "not found": String.format("%s,%s,%s",globalStart,globalEnd,globalGain));

    }



    public static void main(String[] args){
        new TrendBuyAndSell().solution(getInputData());
    }
}
