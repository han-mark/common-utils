package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 选择排序每轮选一个最小值放到本轮最前面
 *
 * @author han
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = Utils.generateRandowArray(50, 1, 50);
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        // 每轮选出最小的
        for (int i = 0; i < array.length; i++) {
            int temp = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[temp]) {
                    temp = j;
                }
            }
            Utils.swap(array, i, temp);
        }
    }
}
