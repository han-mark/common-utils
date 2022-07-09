package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 冒泡排序每轮选一个最小值放到本轮最前面
 *
 * @author han
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {2,3,9,4,11,2,8,10,0,1};
        System.out.println(Arrays.toString(sort(array)));
    }

    private static int[] sort(int[] array) {
        if ( array == null || array.length <= 1) {
            return array;
        }
        // 每轮选出最小的
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    Utils.swap(array, i, j);
                }
            }
        }
        return array;
    }
}
