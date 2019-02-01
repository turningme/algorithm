package org.lkpnotice.turningme.comm.algorithm.joffer;

import java.util.Stack;

/**
 * Created by liujinpeng on 2019/2/1.
 *
 * Problem: given a string "How are you" ,replace the blank char %20  result in "How%20are%20you"
 * it is something like encoding
 */
public class No02StringReplacement {
    static final String KEY_WORD="%20";
    static final char[] KEY_CHARS= "%20".toCharArray();
    char tt = '\0';

    static String getInputData(){
        return "How are you";
    }


    /**
     * 字符串的数组的0-n的顺序，不是数字的对应的低位到高位的顺序
     * @param input
     * @return
     */
    String solution(String input){
        Stack<String> stack = new Stack<String>();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<input.length();i++){
            String tmp = "";
            char tt = input.charAt(i);
            if (tt-' ' == 0){
                tmp = KEY_WORD;
            }else {
                tmp = tt+"";
            }
            stack.push(tmp);
            stringBuffer.append(tmp);
        }


      /*  while(!stack.empty()){
            stringBuffer.append(stack.pop());
        }
        */
        return stringBuffer.toString();
    }




    String solution(char[] input){
       // new String()

        return "";
    }


    public static  void main(String[] args){
        System.out.println(new No02StringReplacement().solution(getInputData()));
    }
}
