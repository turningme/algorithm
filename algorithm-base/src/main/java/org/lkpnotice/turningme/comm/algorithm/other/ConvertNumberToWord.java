package org.lkpnotice.turningme.comm.algorithm.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by liujinpeng on 2019/1/30.
 *
 * Problem : Given a number N, convert it to chinese word , for example, 123 is 一百二十三
 *
 *
 */
public class ConvertNumberToWord {
    static String[] UNIT = new String[]{"","十","百","千"};
    static String[] UNIT_BIG = new String[]{"","万","亿","兆"};
    static Map<Integer,String> digitsMap = new HashMap(){{
        put(0,"零");
        put(1,"一");
        put(2,"二");
        put(3,"三");
        put(4,"四");
        put(5,"五");
        put(6,"六");
        put(7,"七");
        put(8,"八");
        put(9,"九");
    }};


    public String translate(int input){

        Stack<String> stack = new Stack<String>();
        int position = 0;
        int radix = 10;
        int base = 1;



        boolean isUnitAdd =false;
        boolean isZeroSet = false;

        while(input/base >0){
            int digit = (input/base)%radix;
            int bigOffset = position / UNIT.length;
            int offset = position % UNIT.length;

            if (offset == 0){
                if (isUnitAdd &&isZeroSet){
                    stack.push(digitsMap.get(0));
                }

                isUnitAdd =false;
                isZeroSet = false;
            }

            if (digit == 0){
                if (isUnitAdd && isZeroSet){
                    //
                }else if (isUnitAdd && !isZeroSet){
                    isZeroSet = true;
                }else if (!isUnitAdd){
                    //
                }

            }else{
                String tmp = "";
                if (isZeroSet && isUnitAdd){
                    tmp = String.format("%s%s%s",digitsMap.get(digit),UNIT[offset],digitsMap.get(0));
                    isZeroSet = false;
                }else if (isZeroSet && !isUnitAdd){
                    //
                }else if (!isZeroSet && isUnitAdd){
                    tmp = String.format("%s%s",digitsMap.get(digit),UNIT[offset]);
                }else if (!isZeroSet && !isUnitAdd){
                    tmp = String.format("%s%s%s",digitsMap.get(digit),UNIT[offset],UNIT_BIG[bigOffset]);
                    isUnitAdd = true;
                }

                stack.push(tmp);
            }



            position ++;
            base *= 10;
        }


        StringBuilder stringBuilder = new StringBuilder();
        String tmp = "";
        while(!stack.empty()){
            tmp = stack.pop();
            stringBuilder.append(tmp);
        }


        return stringBuilder.toString();
    }


    public static void main(String[] args){
        System.out.println(new ConvertNumberToWord().translate(100003030));
    }

}
