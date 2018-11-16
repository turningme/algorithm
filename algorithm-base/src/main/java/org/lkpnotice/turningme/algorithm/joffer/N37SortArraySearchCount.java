package org.lkpnotice.turningme.algorithm.joffer;

import org.lkpnotice.turningme.algorithm.Execution;

import java.util.Comparator;

/**
 * Created by liujinpeng on 2018/11/15.
 * the focus is bi-search alg.
 * use while ,not recursive function to resolve
 * init :start = 0; end = length-1
 * while condition end >=start to include all kinds of condition
 * there are three kinds of normal situations , 1. start = end 2.start +  1 =end 3. end -start >1
 * 3 situation could deduce to 1 and 2 .
 * for 1 and 2 we handle them specially in while loop to just utilize if judgement..
 *
 *
 * after bi-search ,we locate the key in the array , name it position.
 * the search from positon in array  two directions until find the bounds and count all elements
 *
 */
public class N37SortArraySearchCount implements Execution{



    protected Integer[] mockArray(){
        return new Integer[]{1,2,3};
    }

    protected Integer mockKey(){
        return Integer.valueOf(2);
    }

    public void execute() {
        Comparable[] sortedArray = mockArray();
        Comparable key = mockKey();
        int cnt =statOccuranceInSortedArray(sortedArray,key);
        System.out.println("result is " + cnt);
    }


    protected Integer statOccuranceInSortedArray(Comparable[] sortedArray,Comparable key){
        Integer count = 0;
        if (null == sortedArray || 0 == sortedArray.length ){
            return count;
        }

        //input is legal

        //use bi-part alg to explore the position the key on, - return if not found
        //then deduce pre and after to check the total count
        int postion = positionKeyInArray(sortedArray,key);
        if ( -1 != postion ){
            count = countNumber(sortedArray,postion);
        }


        return count ;
    }


    /**
     *
     *
     * @param sortedArray
     * @param key
     * @return -1 if not found . else the first found position .
     */
    protected int positionKeyInArray(Comparable[] sortedArray, Comparable key){
        int result = -1;

        int start = 0;
        int end = sortedArray.length -1 ;

        while(end >= start){




            int mid = (end +start) /2;
            //handle the situation that first == tail.., need no iteration more...
            if (end - start <=1){
                if (key.compareTo(sortedArray[start]) == 0){
                    result = start;
                }else if (key.compareTo(sortedArray[end]) == 0){
                    result = end;
                }
                break;
            }


            //less ,then the end change to the mid
            if (key.compareTo(sortedArray[mid]) < 0){
                end = mid;
            }else if (key.compareTo(sortedArray[mid]) >0){
                start = mid;
            }else{
                //equal then return the position..
                result = mid;
                break;
            }

        }


        return result;
    }


    /**
     *
     * @param sortedArray
     * @param keyPos
     * @return
     */
    protected  int countNumber(Comparable[] sortedArray, int keyPos){
        int preCnt = 0;
        int alterCnt = 0;
        for (int i = keyPos-1; i>=0;i--){
            if (sortedArray[keyPos].compareTo(sortedArray[i]) ==0){
                preCnt ++;
            }else{
                break;
            }
        }

        for (int j = keyPos +1 ;j<sortedArray.length ;j++){
            if (sortedArray[keyPos].compareTo(sortedArray[j]) ==0){
                alterCnt ++;
            }else{
                break;
            }
        }

        return preCnt + alterCnt +1;
    }






    public static void main(String[] args){
        new N37SortArraySearchCount().execute();
    }
}
