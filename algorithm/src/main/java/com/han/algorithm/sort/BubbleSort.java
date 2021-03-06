package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 冒泡排序 每轮最小值会浮到最前面
 * 冒泡、选择、插入是非常相识的3中排序
 *
 * @author han
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = Utils.generateRandowArray(50, 1, 50);
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    Utils.swap(array, i, j);
                }
            }
        }
    }
}
