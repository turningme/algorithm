package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.List;

/**
 * Created by liujinpeng on 2019/2/1.
 *
 * Problem: given pre-traversal and mid-traversal sequence ,rebuild the binary tree.
 * for example , pre {1,2,4,7,3,5,6,8} mid {4,7,2,1,5,3,8,6}, compute the tree
 *
 *
 *
 *
 */
public class No04RebuildBinaryTree {


    static int[] getPreTraversalNodes(){
         return new int[]{1,2,4,7,3,5,6,8};
    }



    static int[] getMidTraversalNodes(){
        return new int[]{4,7,2,1,5,3,8,6};
    }


    static void preTraverse(Node n){
        if (null == n){
            return;
        }

        System.out.print(n.getVal() + " ");
        preTraverse(n.getLeft());
        preTraverse(n.getRight());
    }


    static void midTraverse(Node n){
        if (null == n){
            return;
        }

        midTraverse(n.getLeft());
        System.out.print(n.getVal() + " ");
        midTraverse(n.getRight());
    }


    static void afterTraverse(Node n){
        if (null == n){
            return;
        }

        afterTraverse(n.getLeft());
        afterTraverse(n.getRight());
        System.out.print(n.getVal() + " ");
    }

    Node solution(int[] pre, int[] mid){
        return getSubTreeRoot(pre,mid);
    }


    Node getSubTreeRoot(int[] pre, int[] mid){

        //bound detect
        if (pre ==null || mid == null){
            return null;
        }

        if (pre.length ==0 || mid.length ==0){
            return null;
        }

        //subtree root
        int root = pre[0];
        Node rootNode = new Node(root);


        int[] midLeft = computeMidLeft(mid,root);
        int[] midRight = computeMidRight(mid,root);
        int[] preLeft = preLeft(pre,midLeft.length);
        int[] preRight = preRight(pre,midLeft.length);
        //left
        Node leftN = getSubTreeRoot(preLeft,midLeft);

        //right
        Node rightN = getSubTreeRoot(preRight,midRight);


        rootNode.setLeft(null);
        if (null !=leftN){
            rootNode.setLeft(leftN);
        }
        rootNode.setRight(null);
        if (null !=rightN){
            rootNode.setRight(rightN);
        }
        return rootNode;
    }


    int[] preLeft(int[] pre,int leftlen){
        int[] result = new int[leftlen];
        for (int i=0;i<leftlen;i++){
            result[i] = pre[i+1];
        }
        return result;
    }


    int[] preRight(int[] pre,int leftlen){
        int newLen = pre.length -1 -leftlen;
        int[] result = new int[newLen];
        for (int i=0; i<newLen ;i++){
            result[i] = pre[i+1+leftlen];
        }
        return  result;
    }


    int[] computeMidLeft(int[] mid,int root){
        int len = 0;
        for (int i=0;i< mid.length;i++){
            if (root == mid[i]){
                break;
            }
            len++;
        }

        int[] result = new int[len];
        for (int i=0 ; i< len ; i++){
            result[i] = mid[i];
        }
        return result;
    }

    int[] computeMidRight(int[] mid,int root){
        int len = 0;
        for (int i=0;i< mid.length;i++){
            if (root == mid[i]){
                break;
            }
            len++;
        }

        int anotherLen = mid.length-1-len;
        int[] result = new int[anotherLen];
        for (int i=0 ; i< anotherLen ; i++){
            result[i] = mid[i+1+len];
        }

        return result;
    }


    static  class  Node {
        Node left;
        Node right;
        int val;

        public Node(Node left, Node right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        public Node(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    static String joinAll(int[] input){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i =0; i<input.length ;i++){
            stringBuffer.append(input[i]).append(" ");
        }
        return stringBuffer.toString();
    }



    public static void main(String[] args){
        System.out.println(String.format("pre = %s",joinAll(getPreTraversalNodes())));
        System.out.println(String.format("mid = %s",joinAll(getMidTraversalNodes())));


        No04RebuildBinaryTree instance = new No04RebuildBinaryTree();
        Node root = instance.solution(getPreTraversalNodes(),getMidTraversalNodes());
        System.out.println();
        preTraverse(root);
        System.out.println();
        midTraverse(root);
        System.out.println();
        afterTraverse(root);
        System.out.println();

    }
}
