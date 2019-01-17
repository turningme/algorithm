package org.lkpnotice.turningme.comm.algorithm.joffer;

import org.lkpnotice.turningme.comm.algorithm.Execution;
import org.lkpnotice.turningme.comm.algorithm.util.LogUtil;
import org.lkpnotice.turningme.comm.algorithm.util.Node;

/**
 * Created by liujinpeng on 2018/11/29.
 *
 * problem:
 *  given a sorted linked list A ,find out the repeated nodes and remove them
 *
 *
 * solution-a:
 *  traverse the list with a hashset, cache node if not exists,  if cached remove it ,otherwise cached
 *
 * solutionn-b: take full advantage of sorted feature ,the decision which node to remove depends on
 * compare the previous one and current one ,if equal remove current ,else continue
 *
 *
 *
 */
public class No56LinkedListRemoveRepeatableNode implements Execution {



    public Node constructLinkedlistA(){

        Node n1 = new Node<Integer>(1);
        Node n2 = new Node<Integer>(2);
        Node n3 = new Node<Integer>(3);
        Node n31 = new Node<Integer>(3);
        Node n4 = new Node<Integer>(4);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n31);
        n31.setNext(n4);

        return n1;
    }


    public void execute() {
        Node nodeHead = constructLinkedlistA();
        trimLinkedListV2(nodeHead);
        print(nodeHead);
    }


    void print(Node nodeHead){
        while(nodeHead!=null){
            LogUtil.doLog(nodeHead.toString());
            nodeHead = nodeHead.getNext();
        }
    }

    /**
     * solution-a
     * with set cached data
     * @param head
     */
    public void trimLinkedList(Node head){
//        Set cache = new HashSet();
//        while(){
//
//        }
    }

    /**
     *
     * sorted is important
     * solution-b
     * @param head
     */
    public void trimLinkedListV2(Node head){
        Node n1 = head;
        Node n2 = head;
        if (head!=null){
            n1 = n1.getNext();
        }

        //init status n2->n1

        while(n1!=null){
            if (n1.getValue() == n2.getValue()){
                // a->b->c  b=a or remove n1  n1.next to n1 n2 not change
                n2.setNext(n1.getNext());
                n1 = n2.getNext();
            }

            // step inc
            n2 = n1;
            n1 = n1.getNext();
        }

    }



    public static void main(String[] args){
        new No56LinkedListRemoveRepeatableNode().execute();
    }



}
