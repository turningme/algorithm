package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/2.
 * Problem: given double type floating  base and int type exponent , compute the exponent of specified base
 * Solution:
 * 整数求幂运算简单
 *
 * 运算指数
 * 快速幂
 */
public class No12FloatNumberExponent {



    double solution(double base , int exp){
        double res = 1;
        for (int i=0;i<exp;i++){
            res *=base;
        }

        return res;
    }


    double solutionRecursivelyAccelerate(double base , int exp){
        if (0==exp){
            return 1;
        }

        if (1==exp){
            return  base;
        }


        return exp>0? solutionRecursivelyAccelerateInner(base,exp):1/solutionRecursivelyAccelerateInner(base,-exp);
    }



    double solutionRecursivelyAccelerateInner(double base , int exp){
        if (exp ==1){
            return base;
        }

        double midResult = solutionRecursivelyAccelerateInner(base,exp>>1);
        return (exp&1)==0? midResult*midResult:midResult*midResult*base;

    }




    double solutionAccelerate(double base , int exp){
        if (0==exp){
            return 1;
        }

        if (0==base||-0==base|| 0.00001==base||-0.00001==base){
            if (exp<0){
                throw new RuntimeException("not legal");
            }
        }

        double tempBase = base;
        double result = 1;
        int e = exp <0? -exp:exp;

        while (e!=0){
            if ((e&1)==1){
                result *= tempBase;
            }

            tempBase = tempBase*tempBase;
            e = e>>1;
        }

        return result;
    }





    public static void main(String[] args){
        int n =222;
        double base = 2.2;
        No12FloatNumberExponent instance = new No12FloatNumberExponent();
        System.out.println(String.format("base:%s  exponent %s ,result is %s",base,n,instance.solution(base,n)));
        System.out.println(String.format("base:%s  exponent %s ,result is %s [recursive accelerate]",base,n,instance.solutionRecursivelyAccelerate(base,n)));
        System.out.println(String.format("base:%s  exponent %s ,result is %s [ accelerate]",base,n,instance.solutionAccelerate(base,n)));

    }
}
