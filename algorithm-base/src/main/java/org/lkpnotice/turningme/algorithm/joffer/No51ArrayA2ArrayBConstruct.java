package org.lkpnotice.turningme.algorithm.joffer;

import org.lkpnotice.turningme.algorithm.Execution;

import static org.lkpnotice.turningme.algorithm.util.PrintUtil.print;

/**
 * Created by liujinpeng on 2018/11/29.
 *
 * problem:
 * A[0,1,...,n-1] n elements
 * B[0,1,...,n1] n elements
 * B[i] = A[0]* ...*A[i-1]*A[i+1]*...*A[n]
 * div operate is not allowed
 *
 * resolution:
 * A == null ; return null
 * A.size = 0; return null
 * A.size = 1; return null
 * A.size > 1  resulotion_a
 *
 */
public class No51ArrayA2ArrayBConstruct implements Execution{

    protected Integer[] mockArray(){
        return new Integer[]{1,2,3};
    }

    public void execute() {
        Integer[] in = mockArray();
        Integer[] out = convert(in);
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

    public static void main(String[] args){
        new No51ArrayA2ArrayBConstruct().execute();
    }

}
