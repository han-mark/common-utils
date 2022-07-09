package com.han.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {2,3,9,4,11,2,8,10,0,1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] >= array[i]) {
                    insert(array, i , j);
                    break;
                }
            }
        }
    }

    /**
     * 将j -- i-1 的数往后移一位， 将原来i上的值放到j上
     * @param array
     * @param i
     * @param j
     */
    private static void insert(int[] array, int i, int j) {
        int origin = array[i];
        for (int k = i; k > j; k--) {
            array[k] = array[k-1];
        }
        array[j] = origin;
    }
}
