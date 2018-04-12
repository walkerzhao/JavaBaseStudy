package com.tencent.java.algorithm.swordoffer;

/**
 * 从一个二维数组中查找指定元素
 * 数组每一行是递增的，每一列也是递增的
 */
public class FindDataFromArray {

    public static void main(String[] args) {

    }

    /**
     * 从右上角开始遍历
     * 当元素大于右上角元素的时候，去掉左边的行；
     * 当元素小于右上角元素的时候，去掉左边的列
     * 注意边界条件处理
     * @param target
     * @param array
     * @return
     */
    public static boolean Find(int target, int [][] array) {
        int rowLength = array.length;
        int columnLength = array[0].length;

        if(columnLength == 0) {
            return false;
        }

        int rowStart = 0;
        int columnStart = columnLength-1;
        while(true) {
            int data = array[rowStart][columnStart];
            if(target == data) {
                return true;
            } else if(target > data){
                rowStart += 1;
            } else {
                columnStart -= 1;
            }

            if(rowStart == rowLength || columnStart == -1) {
                return false;
            }
        }
    }
}
