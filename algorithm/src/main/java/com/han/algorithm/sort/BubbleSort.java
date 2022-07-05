package com.han.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {2,3,9,4,11,2,8,10,0,1};
        System.out.println(Arrays.toString(sort(array)));
    }

    private static int[] sort(int[] array) {
        if ( array == null || array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    private static void swap(int[] array, int i, int j) {
        int origin = array[i];
        array[i] = array[j];
        array[j] = origin;
    }
}
