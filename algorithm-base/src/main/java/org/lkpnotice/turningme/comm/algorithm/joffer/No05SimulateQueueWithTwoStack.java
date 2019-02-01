package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.Stack;

/**
 * Created by liujinpeng on 2019/2/1.
 * 两个栈模拟一个队列和两个队列模拟一个栈
 *
 *
 */
public class No05SimulateQueueWithTwoStack {

    Stack<Integer> inputStack = new Stack<Integer>();
    Stack<Integer> outputStack = new Stack();



    void push(int input){
        inputStack.push(input);
    }



    Integer pop(){

        if (!outputStack.empty()){
            return outputStack.pop();
        }else {
            if (inputStack.empty()){
                return null;

            }else {
                while(!inputStack.empty()){
                    outputStack.push(inputStack.pop());
                }
                return outputStack.pop();
            }

        }
    }


    int size(){
        return inputStack.size() + outputStack.size();
    }


    void solution(){
        No05SimulateQueueWithTwoStack instance = this;
        instance.push(1);
        instance.push(2);
        System.out.println(String.format("  %s ",instance.pop()));

        instance.push(3);
        instance.push(4);
        instance.push(5);

        while (instance.size()>0){

            System.out.println(String.format("  %s ",instance.pop()));
        }

    }


    public static void main(String[] args){
        No05SimulateQueueWithTwoStack instance = new No05SimulateQueueWithTwoStack();
        instance.solution();
    }

}
