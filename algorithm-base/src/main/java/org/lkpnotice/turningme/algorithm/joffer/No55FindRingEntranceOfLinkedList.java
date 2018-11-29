package org.lkpnotice.turningme.algorithm.joffer;

import org.lkpnotice.turningme.algorithm.Execution;
import org.lkpnotice.turningme.algorithm.util.LogUtil;
import org.lkpnotice.turningme.algorithm.util.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liujinpeng on 2018/11/29.
 *
 * problem:
 * given a linked list A, find the entrance of ring if exists,otherwise return null
 *
 * another problem like this is given List A and List B find the shared node if exists, otherwise return null
 *
 * solution_a:
 *  the detect point is that last node of the linked list is null if no ring exists,otherwise it never ends
 *  so use a set to cache nodes traversed , then compare current node with the set to detect existence , until next is null
 *  if a ring exists , we will always find a node that we have cached
 *  but this method need all
 *  complexity : time O(n) if hash O(1) space O(n)
 *
 * solution_b:
 *  slowNode fastNode  O(2) external space to use
 *
 *
 */
public class No55FindRingEntranceOfLinkedList implements Execution{


    public void execute() {
        Node nodeList = constructRingLinkedList();
        Node result = findEntranceOfRing(nodeList);
        if (null == result){
            LogUtil.doLog("No ring exists");
        }else {
            LogUtil.doLog(String.format("ring exists, node: %s", result.toString()));
        }
    }


    protected  Node constructNoRingLinkedList(){
        Node n1 = new Node<Integer>(1);
        Node n2 = new Node<Integer>(2);

        Node n3 = new Node<Integer>(3);
        Node n4 = new Node<Integer>(4);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        return n1;
    }


    protected  Node constructRingLinkedList(){
        Node n1 = new Node<Integer>(1);
        Node n2 = new Node<Integer>(2);

        Node n3 = new Node<Integer>(3);
        Node n4 = new Node<Integer>(4);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        n4.setNext(n2);
        return n1;
    }



    /**
     *
     * @param nodeList
     */
    public Node findEntranceOfRing(Node<Integer> nodeList){
        Set<Node> cache = new HashSet<Node>();

        Node n = nodeList;
        Node target = null;

        while(n != null){
            if (cache.contains(n)){
                target = n;
                break;
            }

            cache.add(n);
            n = n.getNext();
        }

        return target;
    }


    /**
     * optimized methods
     * @param nodeHead
     * @return
     */
    public Node findEntranceOfRingV2(Node<Integer> nodeHead){

        //init check
        if (nodeHead == null || nodeHead.getNext() == null || nodeHead.getNext().getNext() == null){
            return null;
        }

        Node slow = nodeHead.getNext();
        Node fast = slow.getNext();

        while(slow != fast){

            /**
             * check next space is not null
             */
            if (slow.getNext() == null || fast.getNext() == null || fast.getNext().getNext() == null){
                return null;
            }

            slow = slow.getNext();
            fast = fast.getNext().getNext();

        }

        //detect some cycle if arriving here

        fast = nodeHead;
        while(fast != slow){
            fast = fast.getNext();
            slow = slow.getNext();
        }

        return slow;
    }


    public static void main(String[] args){
        new No55FindRingEntranceOfLinkedList().execute();
    }


}
