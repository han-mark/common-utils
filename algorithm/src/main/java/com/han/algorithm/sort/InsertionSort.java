package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 插入排序, 每轮将数组中的值插入一个有序数组中
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {2,3,9,4,11,2,8,10,0,1};
        sort1(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 直接找到合适的位置，插进去
     *
     * @param array
     */
    private static void sort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 直接找到位置，原来及后面的右移
            for (int j = 0; j < i; j++) {
                if (array[j] >= array[i]) {
                    Utils.insert(array, i , j);
                    break;
                }
            }
        }
    }

    /**
     * 从列表中从后往前往后腾一个位置，直到可以插进去
     *
     * @param array
     */
    private static void sort2(int[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            for (j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = temp;
        }
    }

    /**
     * 在有序列表中，每次都交换直到找到合适的位置位置 ， 逆序只需要改下if判断 反向就行了
     *
     * @param array
     */
    private static void sort3(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 找到位置，原来及后面的右移
            for (int j = i ; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    Utils.swap(array, j - 1 , j);
                } else {
                    break;
                }
            }
        }
    }


}
