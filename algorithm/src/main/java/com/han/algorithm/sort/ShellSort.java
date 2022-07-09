package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 希尔排序, 反正里面嵌套了一个插入排序
 *
 * @author han
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = Utils.generateRandowArray(50, 1, 50);
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        for (int step = array.length/2; step > 0 ; step /= 2) {
            // 这里面就是一个完整的插入排序
            for (int i = step; i < array.length ; i ++) {
                for (int j = i; j >= step; j-=step) {
                    if (array[j] < array[j - step]) {
                        Utils.swap(array, j, j - step);
                    }
                }

            }
        }
    }
}
