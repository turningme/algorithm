package org.lkpnotice.turningme.comm.algorithm.joffer;

import org.lkpnotice.turningme.comm.algorithm.Execution;

import static org.lkpnotice.turningme.comm.algorithm.util.PrintUtil.print;

/**
 * Created by liujinpeng on 2018/11/29.
 *
 * problem:
 * A[0,1,...,n-1] n elements
 * B[0,1,...,n1] n elements
 * B[i] = A[0]* ...*A[i-1]*A[i+1]*...*A[n]
 * div operate is not allowed
 *
 * resolution a:
 * A == null ; return null
 * A.size = 0; return null
 * A.size = 1; return null
 * A.size > 1  resulotion_a
 *
 * comments: time complexity is expensive
 *
 * resolution b:  take full advantage of the characteristics, sequence , break point,  sequential sum or multiply as the sub-problem
 * B[i] = A[0]* ...*A[i-1]*A[i+1]*...*A[n]
 *      = (A[0]* ...*A[i-1])      *      (A[i+1]*...*A[n])
 *
 * B[i]_1 = (A[0]* ...*A[i-1])
 * B[i]_2 = (A[i+1]*...*A[n])
 * B[i] = B[i]_1  *  B[i]_2
 */
public class No51ArrayA2ArrayBConstruct implements Execution {

    protected Integer[] mockArray(){
        return new Integer[]{1,2,3};
    }

    public void execute() {
        Integer[] in = mockArray();
        Integer[] out = convertV2(in);
        print(out);
    }

    /**
     *  check whether the input is ok
     *
     * @param in
     * @return false if input array is not satisfied
     */
    protected boolean checkLegal(Integer[] in){
        return in !=null && in.length>1;
    }

    /**
     *
     * @param input
     * @return
     */
    protected Integer[] convert(Integer[] input){
        if (!checkLegal(input)){
            return null;
        }

        int len = input.length;
        Integer[] result = new Integer[len];

        for (int i=0 ; i<len ; i++){
            result[i] = 1;
            for (int j=0 ;j<len ;j++){

                //do multiply if not equal
                if (i!=j){
                    result[i] *= input[j];
                }
            }
        }

        return result;

    }

    /**
     *
     * take full advantage of  sequence , like sequential sum, sequential multiply
     * train your thinking manner
     *
     *  1, 2, 3, 4, 5
     *
     *  normal check
     *
     *  mul(none),mul(1-1),mul(1,2),nul(1,n-2)
     *
     *  reverse check
     *
     *  mul(none),multi(n-1,n-1),multi(n-1,n-2) ,multi(n-1,1)
     *
     *  multiply them one to the corresponding one ,the same index position
     *
     *  mul(non) = 1
     *
     * @param input
     * @return
     */
    protected Integer[] convertV2(Integer[] input){
        if (!checkLegal(input)){
            return null;
        }
        int len = input.length;
        Integer[] result = new Integer[len];

        /**
         * two loops
         */

        result[0] = 1;
        for (int i=0;i< len-1 ; i++){
            result[i + 1] = input[i] * result[i];
        }

        int tmp = 1;
        for (int k=len-1;k>=0;k--){
            result[k] = result[k] * tmp;
            tmp = tmp * input[k];
        }

        return result;
    }

    public static void main(String[] args){
        new No51ArrayA2ArrayBConstruct().execute();
    }

}
