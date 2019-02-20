package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/2/2.
 *
 * Problem:given a linked list ,reverse it and output its new head;
 *
 * 头插法？
 * 尾插法？
 * 链表创建
 * 递归方式实现reverse （300个节点的递归会stackOverflow）
 *
 * K-reverse
 * 对折操作
 *
 */
public class No15ReverseLinkedList {


    /**
     * mock a linked list
     * @return
     */
    static  Node getInputData(){
        Node head = null;
        Node tail = null;
        for (int i=0;i<10;i++){
            if (head == null){
                head = tail = new Node(null,i);
            }else{
                Node newOne = new Node(null,i);
                tail.setNext(newOne);
                tail = tail.getNext();
            }

        }

        return head;
    }


    /**
     *
     * @param head
     * @return
     * 注意循环的循环控制变量
     * 链表注意操作顺序，和操作时候是否会覆盖使用的变量，利用临时变量保存现场
     */
    public Node solution(Node head){
        if (null == head || head.getNext() == null)
            return head;


        Node tmp = head;
        Node newHead = null;
        while(tmp !=null){
            Node nextTmp = tmp.getNext();

            tmp.setNext(newHead);
            newHead = tmp;

            tmp = nextTmp;
        }


        return newHead;
    }


    static void printNodes(Node head){
        StringBuffer stringBuffer = new StringBuffer();
        if (head == null){
            System.out.println("linked list is null");
            return;
        }

        while(head!=null){
            stringBuffer.append(String.format(" %s ",head.getVal()));
            head = head.getNext();
        }

        System.out.println(stringBuffer.toString());
    }



    public static void main(String[] args){
        No15ReverseLinkedList instance = new No15ReverseLinkedList();
        Node data = getInputData();
        printNodes(data);
        Node result = instance.solution(data);

        printNodes(result);
    }





    static class Node{
        Node next;
        int val;

        public Node(Node next, int val) {
            this.next = next;
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}
