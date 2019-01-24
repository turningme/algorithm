package org.lkpnotice.turningme.comm.algorithm.other;

/**
 * Created by liujinpeng on 2019/1/24.
 *https://blog.csdn.net/coolwriter/article/details/79039234
 * Problem：given sorted array  (example.[1,3,4]),find the target value v ,the position k where array[k] = v or just more than or less than it
 *
 * Solution: basic pattern ,
 *  int start =0;
 *  int end = len -1;
 *  while(start <= end ){
 *      int mid = (start + end)/2
 *
 *      if(array[mid]>v){
 *          end = mid -1
 *      }else if (array[mid] < v){
 *          start = mid + 1
 *      }
 *
 * }
 *
 * the last status ,if not found or found in some cases, start = end + 1
 * first start = end then next round start = end + 1
 *
 */
public class BisectionSearchAndVariants {

    /**
     * construct data
     * @return
     */
    static int[] getInputData(){
        return new int[]{1,2,3,3,4,8,11,16,19,22,23};
    }

    static int getTargetValue(){
        return 3;
    }

    /**
     * test if the input is ok
     * @param input
     * @return
     */
    boolean checkLegal(int[] input){
        return  !(null == input || input.length <1);
    }


    /**
     * find the position k where  array[k] = v,v is the target value
     * not repeated values array
     * @param input
     */
    void solutionBase(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                position = mid;
                break;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }

        System.out.println(String.format("position equal %s is @ %s",v,position));

    }


    /**
     * note that input's elements may be repeated ,and find the first position k where array[k] = v
     * @param input
     * @param v
     */
    void solutionFirstPositionEqual(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                end = mid -1;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }


        //resulting state  end - start = 1,not available  position is -1,available
        if (end + 1 <input.length && input[end+1] == v){
            position = end + 1;
        }

        System.out.println(String.format("the first position is array equal %s is %s",v,position));

    }


    /**
     * note that input's elements may be repeated ,and find the last position k where array[k] = v
     * @param input
     * @param v
     */
    void solutionLastPositionEqual(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                start = mid + 1;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }


        //resulting state  end - start = 1,not available  position is -1,available
        if (end >=0 && input[end] == v){
            position = end;
        }

        System.out.println(String.format("the last position is array equal %s is %s",v,position));
    }


    /**
     * note that input's elements may be repeated ,and find the first position k where array[k] >= v
     * @param input
     * @param v
     */
    void solutionFistPositionEqualOrMore(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                end = mid - 1;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }


        //resulting state  end - start = 1,not available  position is -1,available
        if (start <input.length){
            position = start;
        }

        System.out.println(String.format("the last position is array equal %s is %s",v,position));
    }



    /**
     * note that input's elements may be repeated ,and find the first position k where array[k] > v
     * @param input
     * @param v
     */
    void solutionFistPositionMore(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                start = mid + 1;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }


        //resulting state  end - start = 1,not available  position is -1,available
        if (start <input.length){
            position = start;
        }

        System.out.println(String.format("the last position is array equal %s is %s",v,position));
    }


    /**
     * note that input's elements may be repeated ,and find the last position k where array[k] <= v
     * @param input
     * @param v
     */
    void solutionLastPositionEqualOrLess(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                start = mid + 1;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }


        //resulting state  end - start = 1,not available  position is -1,available
        if (end >=0){
            position = end;
        }

        System.out.println(String.format("the last position is array <= %s is %s",v,position));
    }

    /**
     * note that input's elements may be repeated ,and find the last position k where array[k] < v
     * @param input
     * @param v
     */
    void solutionLastPositionLess(int[] input,int v){
        if (!checkLegal(input)){
            System.out.println("input illegal .");
            return ;
        }

        int position = -1;
        int start = 0;
        int end = input.length - 1;
        while(start <= end){
            int mid = (start + end)/2;
            if (input[mid] == v){
                end = mid - 1;
            }else if (input[mid] > v){
                end = mid -1;
            }else if(input[mid] < v){
                start = mid +1;
            }
        }


        //resulting state  end - start = 1,not available  position is -1,available
        if (end >=0){
            position = end;
        }

        System.out.println(String.format("the last position is array < %s is %s",v,position));
    }

    public static void main(String[] args){
        new BisectionSearchAndVariants().solutionLastPositionLess(getInputData(),getTargetValue());
    }

}
