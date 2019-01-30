package org.lkpnotice.turningme.comm.algorithm.leetcode;

/**
 * Created by liujinpeng on 2019/1/30.
 *
 * Problem: count the digits 1 from 1->N ,
 *
 */
public class No233CountOfDigitOne {


    int computeOneByOne(int input){

        int countTotal = 0;
        for (int i = 1 ; i<= input ; i++){
            String iStr = Integer.toString(i);

            int countInner = 0;
            for (int j=0;j<iStr.length();j++){
                if (iStr.charAt(j) == '1'){
                    countInner ++;
                }
            }

            countTotal += countInner;
        }

        return countTotal;
    }


    int computeDistinct(int input){
        int countTotal = 0;
        for (int i = 1 ; i<= input ; i++){
            String iStr = Integer.toString(i);

            int countInner = 0;
            for (int j=0;j<iStr.length();j++){
                if (iStr.charAt(j) == '1'){
                    countInner ++;
                    break;
                }
            }

            countTotal += countInner;
        }

        return countTotal;
    }



    int computeDigitsRoundAlg(int input){

        int count = 0;
        int positon = 0;
        int base = 10;
        int curBase = 1;

        while(input/curBase >0){
            int  nextBase = curBase * base;
            int digit = (input / curBase)%base;
            int digitAfter = input % curBase;
            int digitBefore = input / nextBase;

            int countInner = digitBefore * curBase;
            if ( digit == 1 ){
                countInner += digitAfter + 1;
            }else if ( digit >1 ){
                countInner +=  curBase;
            }

            count += countInner;
            curBase = nextBase;
        }



        return count;
    }



    int computeDigitsNotOne(int input){
        int count = 0;
        int positon = 0;
        int base = 10;
        int curBase = 1;

        int baseMul = 9;
        int curMul =1;

        int minusCount = 0;

        while(input/curBase >0){
            int  nextBase = curBase * base;
            int digit = (input / curBase)%base;
            int digitAfter = input % curBase;
            int digitBefore = input / nextBase;

            int tmpDigit = digit;
            if (digit >1 ){
                tmpDigit -= 1;
            }else if (digit ==1){
                count = 0;
            }


            if (curMul >1){
                count += tmpDigit * curMul;
                minusCount ++;
            }else{
                count +=  (tmpDigit + 1);
            }

            curMul *= baseMul;
            curBase = nextBase;
        }

        if (minusCount >0){
            count = count -1;
        }


        return count;
    }


    /**
     * It is not right ..
     * @param input
     * @return
     */
    int computeDigitsRoundDistinctAlg(int input){

        int count = 0;
        int positon = 0;
        int base = 10;
        int curBase = 1;

        while(input/curBase >0){
            int  nextBase = curBase * base;
            int digit = (input / curBase)%base;
            int digitAfter = input % curBase;
            int digitBefore = input / nextBase;

            if (digit >1){
                count += curBase *(digitBefore +1);
            }else if (digit ==1){
                count += digitAfter;
            }

            curBase = nextBase;
        }



        return count;
    }



    public static void main(String[] args){
        No233CountOfDigitOne compute = new No233CountOfDigitOne();
        int  count = 2000;


        System.out.println(String.format("all digits count which = one %s",compute.computeOneByOne(count)));
        System.out.println(String.format("all digits count which = one Round %s",compute.computeDigitsRoundAlg(count)));
        System.out.println(String.format("all number count which = one %s",compute.computeDistinct(count)));
        System.out.println(String.format("all digits count which = one Round Distinct  %s",count  - compute.computeDigitsNotOne(count)));


    }

}
