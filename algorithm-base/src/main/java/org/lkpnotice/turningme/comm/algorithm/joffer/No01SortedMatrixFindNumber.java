package org.lkpnotice.turningme.comm.algorithm.joffer;

/**
 * Created by liujinpeng on 2019/1/24.
 *
     * Problem: given an matrix,which is n*n , row and column is sorted as asc,find int k, if k is in the matrix
 * [1,2,3,4]
 * [2,3,4,5]
 * [3,4,5,6]
 * [4,5,6,7]
 * Solution: n*n 矩阵，行列都是升序，所以对角线也是升序，从 [0,0] 到 [n-1,n-1] 开始比较 如果小于[0,0]肯定没有，大于 [n-1,n-1] 也没有
 * 可以结合二分查找，做两个版本，先做一个线性遍历查找，再做一个二分查找版本，利用有序的特点
 * 突破n2，抵达线性的计算
 * 二分法就是3logn = O(logn)
 * 可以认为是与二分法殊途同归，都是利用一定的约束顺序，减枝，跳过不需要检测的空间
 *
 *
 * 还一个方法是每一个 L 型都是一个有序的数组，从搜索空间的角度来考虑
 *
 *
 *
 */
public class No01SortedMatrixFindNumber {

    static int[][] getInputData(){
        return new int[][]{
                {1,2,3,4},{2,3,4,5},{3,4,5,6},{4,5,6,7}
        };
    }


    String sulutionLinear(int[][] input,int size ,int key){
        int midPosition = -1;
        for(int n = 0; n <size; n++){
            int tmp = input[n][n];
            if (tmp == key){
                return String.format("(%s,%s)",n,n);
            }else if (key < tmp){
                midPosition = n;
            }
        }

        if (0==midPosition){
            return String.format("not found too small");
        }


        if (-1 == midPosition){
            return String.format("not found too big");
        }


        //find the diagonal position is between 1 and n-1


        //the row
        for(int i=0;i<midPosition;i++){
            int rowEle = input[midPosition][i];
            int colEle = input[i][midPosition];
            if (rowEle == key){
                return String.format("(%s,%s)",midPosition,i);
            }
            if (colEle == key){
                return String.format("(%s,%s)",i,midPosition);
            }
        }


        return String.format("Not found int the n*n matrix");
    }


    /**
     * search the space ,cut the branch one line or column start from the left-bottom or right-top point
     * @param input
     * @param size
     * @param key
     * @return
     */
    String sulutionLShapeSearch(int[][] input,int size ,int key){
        int startX =0;
        int startY = size-1;
        while(startX>=0 && startX<size && startY >=0 && startY <size){
            int temp = input[startX][startY];
            if (temp == key){
                return String.format("(%s,%s)",startX,startY);
            }else if (temp >key){
                startY -= 1;
            }else {
                startX +=1;
            }
        }

        return String.format("Nod found ");
    }






    public static  void main(String[] args){
        System.out.println(new No01SortedMatrixFindNumber().sulutionLShapeSearch(getInputData(),4,3));
    }
}
