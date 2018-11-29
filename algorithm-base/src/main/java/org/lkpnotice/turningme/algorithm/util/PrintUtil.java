package org.lkpnotice.turningme.algorithm.util;

import java.util.*;

/**
 * Created by liujinpeng on 2018/11/29.
 */
public class PrintUtil
{
    public static void print(Object o,StringBuffer stringBuffer){
        if(o.getClass().isArray()){
            List target = new ArrayList(Arrays.asList((Object[])o));
            print(target,stringBuffer);
        } else if (List.class.isAssignableFrom(o.getClass())){
            //print list
            stringBuffer.append(" [ "  );
            int cnt = ((List)o).size();
            for (Object e :(List)o
                 ) {
                print(e,stringBuffer);
                if (--cnt > 0){
                    stringBuffer.append(",");
                }

            }

            stringBuffer.append(" ] "  );

        }else if (Map.class.isAssignableFrom(o.getClass())){
            Iterator<Map.Entry<Object,Object>> iter = ((Map)o).entrySet().iterator();
            stringBuffer.append(" { "  );

            int cnt = ((Map)o).size();
            while(iter.hasNext()){

                Map.Entry<Object,Object> en =  iter.next();
                print(en.getKey(),stringBuffer);
                stringBuffer.append(" : " );
                print(en.getValue(),stringBuffer);
                if (--cnt > 0){
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append(" } "  );

        } else if(null == o){
            stringBuffer.append("   null  ");

        } else {
            //direct output
            stringBuffer.append("   " + o.toString() + "  ");
        }
    }


    public static void  print(Object o){
        StringBuffer sbuf = new StringBuffer();
        print(o,sbuf);
        System.out.println(sbuf.toString());
    }

}
