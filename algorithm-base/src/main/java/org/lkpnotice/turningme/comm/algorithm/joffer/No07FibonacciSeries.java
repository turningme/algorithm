package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/1.
 *
 * Problem:given number n, get the n-th item of the Fibonacci , start from 0 1 1 2
 * solution:
 * 递归
 * 非递归
 *
 *
 */
public class No07FibonacciSeries {




    int solutionRecursive(int n){
        if (n<0){
            throw  new RuntimeException("No legal");
        }

        if (0 == n){
            return 0;
        }

        if (1 == n){
            return 1;
        }

        int nMinus1 = solutionRecursive(n-1);
        int nMinus2 = solutionRecursive(n-2);
        return nMinus1 + nMinus2;
    }



    int solutionNoRecursive(int n){
        if (n<0){
            throw  new RuntimeException("No legal");
        }

        if (0 == n){
            return 0;
        }

        if (1 == n){
            return 1;
        }


        int ppre = 0; int pre = 1;

        for (int i=2;i<=n;i++){
            int temp = pre +ppre;
            ppre = pre;
            pre = temp;
        }

        return pre;

    }


    public static  void main(String[] args){
        int n = 30;
        No07FibonacciSeries  instance = new No07FibonacciSeries();
        System.out.println(String.format("Fibonacci recursively %s is %s",n,instance.solutionRecursive(n)));
        System.out.println(String.format("Fibonacci none-recursively %s is %s",n,instance.solutionNoRecursive(n)));
    }

}
