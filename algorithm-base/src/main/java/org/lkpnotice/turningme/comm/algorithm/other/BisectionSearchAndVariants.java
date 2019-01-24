package org.lkpnotice.turningme.comm.algorithm.other;

/**
 * Created by liujinpeng on 2019/1/24.
 *https://blog.csdn.net/coolwriter/article/details/79039234
 * Problem：given sorted array  (example.[1,3,4]),find the target value v ,the position k where array[k] = v or just more than or less than it
 *
 * Solution: basic pattern ,
 *  int start =0;
 *  int end = len -1;
 *  while(start <= end ){
 *      int mid = (start + end)/2
 *
 *      if(array[mid]>v){
 *          end = mid -1
 *      }else if (array[mid] < v){
 *          start = mid + 1
 *      }
 *
 * }
 *
 */
public class BisectionSearchAndVariants {

    /**
     * construct data
     * @return
     */
    static int[] getInputData(){
        return new int[]{1,2,3,8,11,16,19,22,23};
    }

    static int getTargetValue(){
        return 3;
    }

    /**
     * test if the input is ok
     * @param input
     * @return
     */
    boolean checkLegal(int[] input){
        return  !(null == input || input.length <1);
    }


    /**
     * find the position k where  array[k] = v,v is the target value
     * not repeated values array
     * @param input
     */
    void solutionBase(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                position = mid;
                break;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }

        System.out.println(String.format("position equal %s is @ %s",v,position));

    }




    public static void main(String[] args){
        new BisectionSearchAndVariants().solutionBase(getInputData(),getTargetValue());
    }

}
