package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/2.
 *
 * Problem: given an linked list,find the k-th node value from the tail
 * solution:
 * if k is more than the size ,throw exception
 * if k is less than the size , set two cursor first and second ,first go fast k steps  than the second one
 * then when first reach the end ,the second cursor point to the specified node
 *
 */
public class No14LinkedListFindBottomKNode {

    static  Node getData(){
        Node head = null;
        Node tail = head;

        for (int i=0;i<10;i++){
            Node temp = new Node("data_"+i);
            if (tail ==null){
                head = tail = temp;
            }else{
                tail.next = temp;
                tail = temp;
            }
        }

        return head;
    }




    Node getTheLastKNode(Node input,int k){
        Node first = input;
        Node second = first;
        if (k<1 || input==null){
            throw new RuntimeException("illegal information");
        }

        int count=1;
        while(first.getNext()!=null){
            if (count!=k){
                count++;
            }else {
                second = second.getNext();
            }
            first = first.getNext();
        }


        if (count!=k){
            throw  new RuntimeException("illegal information,k is larger than the size");
        }

        return second;
    }



    static class  Node{
        String v;
        Node next;

        public Node(String v) {
            this.v = v;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
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
                    "v='" + v + '\'' +
                    '}';
        }
    }


    static void print(Node t){
        Node temp = t;
        while(temp!=null){
            System.out.print(String.format("   %s",temp.getV()));
            temp = temp.next;
        }
    }




    public static void main(String[] args){
        No14LinkedListFindBottomKNode instance = new No14LinkedListFindBottomKNode();
        print(getData());
        System.out.println();
        System.out.println(instance.getTheLastKNode(getData(),2).getV());
    }
}
