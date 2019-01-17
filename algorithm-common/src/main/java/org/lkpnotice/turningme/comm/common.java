package org.lkpnotice.turningme.comm;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by liujinpeng on 2019/1/11.
 */
public class common {

    static void test1(){
        Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
        String[] newArray = {"中山", "汕头", "广州", "安庆", "阳江", "南京", "武汉", "北京", "安阳", "北方"};
        List<String> list = Arrays.asList(newArray);
        Collections.sort(list, com);
        for (String i : list) {
            System.out.print(i + "  ");
        }
    }

    public static float floatAdd(float add,float toAdd){
        BigDecimal bda = new BigDecimal(String.valueOf(add));
        BigDecimal bdb = new BigDecimal(String.valueOf(toAdd));
        return bda.
                add(bdb).floatValue();
    }

    static void test2(){
        System.out.println(floatAdd(1.333334f,1.223f));
    }



    public static void main(String[] args) {
        test2();
    }

}
