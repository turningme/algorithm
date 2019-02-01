package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 只是因为生疏了，没有热度了么，还是理解得不够透彻
 * 输入一个数组，按照链表从尾到头，返回一个 Arraylist
 *
 * 可以链表reverse 用栈操作等
 *
 * Created by liujinpeng on 2019/2/1.
 */
public class No03ConvertLinkedList2ArrayList {


    static Node getInputData(){
        Node head = null;
        Node tail =null;
        int count =10;
        for (int i=0; i<count ;i++){
            Node node = new Node(i);
            if (null == tail){
                tail = node;
                head = tail;
            }else {
                tail.next =node;
                tail = node;
            }
        }

        return head;
    }


    static Node reverse(Node input){
        Node newHead = null;

        while(input!=null){
            Node nextInput = input.getNext();
            Node currentInput = input;


            currentInput.next = newHead;
            newHead = currentInput;

            input = nextInput;
        }

        return newHead;
    }


    List solutionFromHead2Tail(Node input){
        Node temp = input;

        List result = new ArrayList();
        while (temp != null){
            result.add(temp);
            temp = temp.next;
        }
        return result;
    }


    static void printArrays(List input){
        StringBuffer stringBuffer = new StringBuffer();

        for (int i =0; i< input.size() ; i++){
            stringBuffer.append(input.get(i).toString()).append("\n");
        }
        System.out.println(stringBuffer.toString());
    }

    static class Node{
        int value;
        Node next;


        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value + "}";
        }




        public static void main(String[] args){
            printArrays(new No03ConvertLinkedList2ArrayList().solutionFromHead2Tail(reverse(getInputData())));
        }
    }
}
