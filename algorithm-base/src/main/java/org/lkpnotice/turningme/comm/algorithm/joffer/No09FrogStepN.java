package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/2.
 *
 * Problem: A frog could jump one ,two ... n steps once time ,so how many kinds of  composite mode it can make to N steps
 *
 * solution:
 * 利用函数递推 0 =1 1=1  f(n) = 2* f(n-1)
 * 跟上一题一样 根据子问题和递归推导出数项的递推关系，然后可以利用迭代正向求解
 *
 */
public class No09FrogStepN {

    int solutionRecursive(int n){
        if (n<0){
            System.out.println("n is less than zero");
            return 0;
        }

        if (0==n){
            return 1;
        }

        int sum = 0;
        for (int i=1;i<=n ;i++){
            sum += solutionRecursive(n-i);
        }

        return sum;
    }








    public static void main(String[] args){
        int stepN = 4;
        No09FrogStepN instance = new No09FrogStepN();
        System.out.println(String.format("for steps %s, the solution is %s",stepN,instance.solutionRecursive(stepN)));
    }
}
