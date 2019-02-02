package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/2.
 *
 *
 * Problem: for examle 3 , in binary is  0011 ，and the count of  digit 1 of 3's binary expr is 2
 * Solution: compute the integer N for the number of 1;
 *
 *
 *
 *
 */
public class No11TheBinary1CountOfInteger {




    int solutionGeneral(int n){
        int temp = n;

        int count = 0;
        while (temp!=0){

            int inner = temp & 1;
            if (inner !=0){
                count++;
            }
            temp = temp>>>1;

        }

        return count;
    }


    int solutionAccelerate(int n ){
        int temp = n;

        int count = 0;
        while (temp!=0){

            count ++;
            temp =(temp-1)&temp;
        }

        return count;

    }



    public static void main(String[] args){
        int n = 7;
        No11TheBinary1CountOfInteger instance = new No11TheBinary1CountOfInteger();
        System.out.println(String.format("Digit 1 count similar of %s is %s",n,instance.solutionGeneral(n)));
        System.out.println(String.format("Digit 1 count accelerate of %s is %s",n,instance.solutionAccelerate(n)));
    }
}
