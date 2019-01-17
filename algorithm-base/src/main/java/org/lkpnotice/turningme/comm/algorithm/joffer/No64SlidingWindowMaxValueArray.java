package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2018/11/30.
 *
 * Problem:
 *  given a sequence , find out all the sub sequence with window x, return the sequence consisted of max value of all the windows
 *  for example , {2,3,4,2,6,2,5,1}  window size is 3 , the we can find out 6 window {2,3,4} {3,4,2} {4,2,6}
 *  {2,6,2} {6,2,5} {2,5,1}
 *
 *  sequence consisted of all the max value is {4,4,6,6,6,5}
 *
 *
 * Solution-a:
 *    split the main sequence as sub sequences ,and find the max value of each
 *
 *    (Size_input - size_widow +1) *size_widow is the space used
 *
 *
 * Solution-b:
 *     feature, sequence , sequential ,
 *
 */
public class No64SlidingWindowMaxValueArray {
}
