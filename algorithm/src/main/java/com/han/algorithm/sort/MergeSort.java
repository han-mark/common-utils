package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 归并排序 将数组递归拆成俩个，然后将俩个有序的数组归并成一个有序数组
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = Utils.generateRandowArray(50, 1, 50);
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int array[]) {
        // 一个元素时一定有序
        if (array.length == 1) {
            return;
        }
        int split = array.length/2;
        int left[] = new int[split];
        int right[] = new int[array.length - split];
        for (int i = 0; i < array.length; i++) {
            if (i < split) {
                left[i] = array[i];
            } else {
                right[i - split] = array[i];
            }
        }
        // 递归拆及合并
        sort(left);
        sort(right);
        merge(left, right, array);
    }

    private static void merge(int left[], int right[], int array[]) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < array.length; k++) {
            if (i == left.length) {
                array[k] = right[j];
                j++;
            } else if (j == right.length) {
                array[k] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
        }
    }
}
