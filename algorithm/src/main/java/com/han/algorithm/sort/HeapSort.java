package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 堆排序 堆 完全二叉树 且根节点总是大于等于（或者小于等于）子节点（叶节点除外）
 * 完全二叉树 最后一层从右边开始缺少叶节点
 * 满二叉树  叶节点除外的节点都有俩个子节点
 *
 * @author han
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = Utils.generateRandowArray(100000000, 1, 100000000);
        long begin = System.currentTimeMillis();
        sort(array);
        System.out.println(System.currentTimeMillis() - begin);
    }

    private static void sort(int[] array) {

        // 从最后一个非叶子节点开始转成大顶堆，最后将整棵树转成大顶堆
        for (int i = (array.length - 1 - 1)/2; i >= 0 ; i--) {
            shiftDown(array, i, array.length);
        }
        // 将大顶堆的顶（0）和最后一个元素（n-1）互换，然后将剩下（n-1-1）转为大顶堆，重复到堆只剩根节点
        for (int i = array.length - 1; i > 0 ; i--) {
            Utils.swap(array, 0, i);
            shiftDown(array, 0, i);
        }
    }

    /**
     * 若已i 为根节点的子树都是堆，则shiftDown 将可将i 为根节点的树 转成堆
     * @param array
     * @param i
     * @param n
     */
    private static void shiftDown(int[] array, int i, int n) {
        while (2 * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && array[j] < array[j + 1]) {
                j ++;
            }
            if (array[i] > array[j]) {
                break;
            }
            Utils.swap(array, i, j);
            i = j;
        }
    }
}
