package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/1.
 *
 * Problem: array{ 3,4,5,1,2} is a rotated array of {1,2,3,4,5} the minimum value is 1
 * given the rotated array ,find the minimum value ,return 0 if array is blank
 *
 * solution: like binary search
 *
 *
 */
public class No06RotatedArray {


    static int[] getInputData(){
        return new int[]{1,1,1,1,1};
    }



    static int[] getInputDataNotRepeated(){
        return new int[]{30,40,50,60,70,2,5,6,7,8,9,10,11,12};
    }

    int solutionWithRepeated(int[] input){
        if (input ==null || input.length ==0){
            return 0;
        }

        if (input.length ==1){
            return input[0];
        }

        int left = 0;
        int right= input.length-1;

        while(right - left  != 1){
            int mid = (left+right)/2;
            if (input[mid]>input[left]){
                left = mid;
            }else if (input[mid]<input[left]){
                right = mid;
            }else{
                int temp = mid;
                while(temp <right && input[temp] == input[temp+1]){
                    temp++;
                }

                if (temp == right){
                    right = mid;
                }else {
                    left = mid;
                }

            }
        }

        return input[right];
    }




    int solutionNoRepeated(int[] input){
        if (input ==null || input.length ==0){
            return 0;
        }

        if (input.length ==1){
            return input[0];
        }

        int left = 0;
        int right= input.length-1;

        while(right - left  != 1){
            int mid = (left+right)/2;
            if (input[mid]>input[left]){
                left = mid;
            }else {
                right = mid;
            }
        }

        return input[right];
    }



    public static  void main(String[] args){
        No06RotatedArray instance = new No06RotatedArray();
        System.out.println(String.format("no repeated case,the minimum element is %s ",instance.solutionNoRepeated(getInputDataNotRepeated())));
        System.out.println(String.format("no repeated case,the minimum element is %s ",instance.solutionWithRepeated(getInputData())));
    }

}
