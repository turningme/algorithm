package org.lkpnotice.turningme.comm.algorithm.other;

/**
 * Created by liujinpeng on 2019/1/25.
 *
 *
 * Problem:given one number , for example  2 which is 0x00000010 in binary format.
 *
 *
 *
 */
public class NumberBitsCountingOneOrZero {


    static int getInputData(){
        return 20;
    }


    /**
     * bit operations like shift
     * @param input
     * @return
     */
    static int bitCountByLogicShift(int input){
        int size = Integer.SIZE;
        int tmp = 1;
        tmp = tmp << (size-1);
        int sum = 0;
        for (int j=0 ; j< size ;j++){
            sum += (tmp&input) >0? 1:0;
            tmp = tmp >>> 1;
        }

        return sum;
    }




    static int bitCountOneByInc(int x){

        int count = 0;
        while(x!=0){
            count++;
            x= x&(x-1);
        }
        return count;
    }



    static int bitCountZeroByInc(int x){
        return Integer.SIZE - bitCountOneByInc(x);
    }




    /**
     * violence method
     * @param input
     * @return
     */
    static int bitCountByRough(int input){
        int n = input;

        n = (n &0x55555555) + ((n >>1)&0x55555555) ;
        n = (n &0x33333333) + ((n >>2)&0x33333333) ;
        n = (n &0x0f0f0f0f) + ((n >>4)&0x0f0f0f0f) ;
        n = (n &0x00ff00ff) + ((n >>8)&0x00ff00ff) ;
        n = (n &0x0000ffff) + ((n >>16)&0x0000ffff) ;

        return n;
    }

    static String toBinaryString(int i){
        StringBuffer stringBuffer = new StringBuffer();
        int size = Integer.SIZE;
        int tmp = 1;
        tmp = tmp << (size-1);
        for (int j=0 ; j< size ;j++){
            stringBuffer.append( (i&tmp) !=0? "1":"0");
            tmp = tmp >>> 1;
        }

        return stringBuffer.toString();
    }

    static void printBinaryBits(int i){
        System.out.println(Integer.toBinaryString(i));
    }


    static void testBitShifts(){
        final int std = -22;
        int input = std;
        System.out.println(input >> 1);
        input = std;
        System.out.println(input >>> 1);
        input = std;
        System.out.println(input << 1);
    }


    public static void main(String[] args){
        /*System.out.println(Integer.MIN_VALUE);
        System.out.println(-1&0x80000000);*/
        printBinaryBits(7);
        System.out.println(toBinaryString(7));
        System.out.println(bitCountZeroByInc(7));
    }
}
