package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by liujinpeng on 2018/11/29.
 *
 * Question: matrix as m*n , one robot move up down left or right, but its position(x,y) all the digits should not > k
 * for example k = 18, (35,37) res = 3+5 + 3 + 7 =18 is ok but (35,38) res = 3 + 5 +3 + 8 = 19 is not okay.
 * how many positions could the robot occupy?
 *
 * Solution:
 *
 *
 *
 */
public class No66MatrixMNDigitsSumNoMore18 {






    public void solution(int k,int m,int n){
        Set<Node> availables = new TreeSet<Node>();
        Stack<Node> stack = new Stack<Node>();

        //init position
        Node start = new Node(0,0);
        availables.add(start);
        stack.push(start);
        while(stack.size()>0){
            Node begin = stack.pop();
            Node tmp = null;
            //up bound
            if (begin.getY()-1>=0){
                tmp = new Node(begin.getX(),begin.getY()-1);
                if (!availables.contains(tmp) && tmp.getDigitsAccumulate()<=k){
                    availables.add(tmp);
                    stack.push(tmp);
                }

            }

            //left bound
            if (begin.getX() - 1 >=0){
                tmp = new Node(begin.getX()-1,begin.getY());
                if (!availables.contains(tmp) && tmp.getDigitsAccumulate()<=k){
                    availables.add(tmp);
                    stack.push(tmp);
                }
            }

            //down bound
            if (begin.getY() +1 < n){
                tmp = new Node(begin.getX(),begin.getY()+1);
                if (!availables.contains(tmp) && tmp.getDigitsAccumulate()<=k){
                    availables.add(tmp);
                    stack.push(tmp);
                }
            }




            //right bound
            if (begin.getX() +1 < m){
                tmp = new Node(begin.getX() + 1,begin.getY());
                if (!availables.contains(tmp) && tmp.getDigitsAccumulate()<=k){
                    availables.add(tmp);
                    stack.push(tmp);
                }
            }

        }

        System.out.println(String.format("size of robot occupied %s",availables.size()));
        //printNodeArray(availables.toArray(new Node[]{}));
    }







    static class Node implements Comparable<Node>{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            return y == node.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }


        public int getDigitsAccumulate(){
            int sum = 0;
            String tmp = Integer.toString(this.x);
            for (int i=0; i < tmp.length();i++){
                sum += tmp.charAt(i) - '0';
            }

            tmp = Integer.toString(this.y);
            for (int i=0; i < tmp.length();i++){
                sum += tmp.charAt(i) - '0';
            }
            return sum;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }


        public int compareTo(Node o) {
            if (getX()>o.getX()){
                return 1;
            }else if (getX() <o.getX()){
                return -1;
            }else {
                return getY()>o.getY()? 1: getY()<o.getY()? -1:0;
            }
        }
    }

    static void testAccumulate(){
        System.out.println(new Node(1,2).getDigitsAccumulate());
    }

    static void printNode(Node n){
        System.out.println(n.toString());
    }

    static void printNodeArray(Node[] array){
        for (int i=0 ; i< array.length ;i++){
            printNode(array[i]);
        }
    }

    public static void main(String[] args){
        new No66MatrixMNDigitsSumNoMore18().solution(18,Integer.MAX_VALUE,Integer.MAX_VALUE);
        //testAccumulate();
    }

}
