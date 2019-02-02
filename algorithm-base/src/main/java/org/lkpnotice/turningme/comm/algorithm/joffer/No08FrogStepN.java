package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/1.
 *
 * Problem: A frog can jump one step or two , so  n steps how many kinds of jumping cound be done?
 *
 * Solution:
 * 子问题，递归，动态规划，数学算式等各种方法
 * 规律是一个 Fibonacii 数列，所以可以用递归或者通过递归找到自底向上的逻辑，利用迭代实现
 * 递归可能会
 *
 */
public class No08FrogStepN {



    int solutionRecursive(int n){
        if (n<0){
            System.out.println("n is less than zero");
            return 0;
        }

        if (0 == n){
            return 1;
        }

        if (n==1){
            return 1;
        }

        return solutionRecursive(n-1) +solutionRecursive(n-2);
    }





    static int com(int m,int n){
        int i=m;
        int sum =1;
        for(int j=0;j<n;j++,i--){
            sum = sum*i/(j+1);
        }
        return sum;
    }



    int solutionCombo(int n){
        int count= 0;

        for (int two=0;two<=(n/2);two++){
            count += com(n-two,two);
        }
        return  count;
    }



    public static void main(String[] args){
        int stepN = 30;
        No08FrogStepN instance = new No08FrogStepN();
        System.out.println(String.format("for steps %s, the solution is %s",stepN,instance.solutionRecursive(stepN)));
        System.out.println(String.format("for steps %s, the solution combo is %s",stepN,instance.solutionCombo(stepN)));


    }
}
