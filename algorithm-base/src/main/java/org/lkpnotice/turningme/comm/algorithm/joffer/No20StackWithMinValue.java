package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by liujinpeng on 2019/2/20.
 *
 * Problem : compound stack A ,which contains basis of stack , additional min function  .复合栈，基于基本数据结构添加一些新鲜的功能
 *
 * solution:双栈的思想，1:1 还是 m:n ， 栈底是0 ，当前位置是n。 辅助栈记录当前 (0,n) 栈中序列的最小值，单调不增
 * 进栈：如果栈是空的，那么  valStack 和 minStack 都进栈。 如果栈非空，小于当前值，直接进minStack，等于的话，栈顶部元素的count 计数+1 表示同一个元素作为当前局部最小值第 n次进栈，大于的话
 * minStack 不操作
 * 出栈：如果不是空的，判断出栈元素，如果大于minstack 栈顶元素，则直接出栈，如果等于，取出 minStack 栈顶，判断count -1 如果不为0重新push到 minStack的顶
 * 如果小于，这种比较方式不会有小于
 *
 * 整体思路应该没有问题，细节把握在等于这一步
 *
 */
public class No20StackWithMinValue {
    Stack<Integer> valueStack = new Stack<Integer>();
    Stack<MinNode<Integer>> minStack = new Stack<MinNode<Integer>>();


    static List<Integer> getInputData(){
        List<Integer> data = new ArrayList<Integer>();
        data.add(1);
        data.add(2);
        data.add(0);
        data.add(1);
        data.add(0);


        return data;
    }


    public void push(Integer e){
        /**
         * 栈为空 push时候 两个栈都初始化
         */
        if (valueStack.empty()){
            minStack.push(new MinNode<Integer>(e));
        }else {//非空
            MinNode<Integer> minVal = minStack.peek();
            if (minVal.getV() == e){//count++
                minVal.setCount(minVal.getCount() +1);
            }else if (e < minVal.getV() ){
                minStack.push(new MinNode<Integer>(e));
            }

        }



        valueStack.push(e);
    }


    public Integer pop(){
        Integer popVal = valueStack.pop();

        MinNode<Integer> minNode = minStack.pop();

        //等于则看count
        if (popVal == minNode.getV()){
            int count = minNode.getCount();
            count --;

            //多次重复作为最小值， 重新push进去，count值-1
            if (count>0){
                minNode.setCount(count);
                minStack.push(minNode);
            }

        }else{

            //恢复栈顶
            minStack.push(minNode);
        }


        return popVal;
    }


    public int size(){
        return this.valueStack.size();
    }

    public boolean empty(){
        return this.valueStack.empty();
    }


    public Integer minVal(){
        return this.minStack.peek().getV();
    }


    public String toString(){
        String tpl = "minval:%s , stack val: %s , stack min: %s";
        String result = "";
        if (this.empty()){
            result = String.format(tpl,null,null,null);
        }

        Integer min = this.minVal();
        int len = size();
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<len;i++){
            Integer ee = this.valueStack.elementAt(i);
            buffer.append(String.format(" %s ",ee));
        }

        StringBuffer bufferMin = new StringBuffer();
        len = this.minStack.size();
        for (int i=0;i<len;i++){
            MinNode<Integer> ee = this.minStack.elementAt(i);
            bufferMin.append(String.format(" %s ",ee.toString()));
        }


        result = String.format(tpl,min,buffer.toString(),bufferMin.toString());
        return result;
    }


    public static void main(String[] args){
        No20StackWithMinValue instance = new No20StackWithMinValue();
        List<Integer> data = getInputData();
        for (Integer e:data
             ) {
            instance.push(e);
        }

        System.out.println(instance.toString());
    }


    static class MinNode<T>{
        T v;
        int count;

        public MinNode(T v, int count) {
            this.v = v;
            this.count = count;
        }

        public MinNode(T v) {
            this(v,1);
        }

        public T getV() {
            return v;
        }

        public void setV(T v) {
            this.v = v;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String toString(){
            return String.format("Val: %s,count: %s ",getV(),getCount());
        }
    }
}
