package com.han.algorithm.sort;

import com.han.algorithm.utils.Utils;

import java.util.Arrays;

/**
 * 快速排序
 * 每轮从左或者右选一个基准值，从俩边开始将数组分成俩部分，让没边都是</> 基准值
 * 最后将基准值与分界处的值交换
 * 每轮剔除分界值
 *
 * @author han
 */
public class QuickSort {

    public static void main(String[] args) {
        int array[] = {4,2,5,6,3,1,8};
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int begin, int end) {
        if ( begin >= end) {
            return ;
        }
        int split = rightFirstStandardRight(array, begin, end);

        // 每次把标准值放到分割位上，下次循环排除分割位
        sort(array, begin, split - 1);
        sort(array, split + 1, end);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 标准值放左边， 先从右边开始
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private static int rightFirstStandardLeft(int[] array, int begin, int end) {
        int left = begin;
        int split = array[left];
        for (;begin < end;) {
            for (; begin < end && array[end] > split; ) {
                end--;
            }
            // 最后标准值要和分界下标交换值， 所有标准值位置不能变，等号放左边
            for (; begin < end && array[begin] <= split; ) {
                begin++;
            }
            if (begin < end) {
                Utils.swap(array, begin, end);
            }
        }
        Utils.swap(array, left, end);
        return end;
    }

    /**
     * 标准值放左边， 先从左边开始
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private static int leftFirstStandardLeft(int[] array, int begin, int end) {
        int left = begin;
        int split = array[left];
        for (;begin < end;) {
            for (; begin < end && array[begin] <= split; ) {
                begin++;
            }
            for (; begin < end && array[end] > split; ) {
                end--;
            }
            if (begin < end) {
                Utils.swap(array, begin, end);
            }
        }
        // 从小到大排序， 先左后右 需对分界值做判断, 会出现分界值大于标准值的情况
        //  例如：1、6、5，不做处理会造成6、换位
        if (array[left] < array[end]) {
            end--;
        }
        Utils.swap(array, left, end);
        return end;
    }

    /**
     * 标准值放右边， 先从左边开始
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private static int leftFirstStandardRight(int[] array, int begin, int end) {
        int left = end;
        int split = array[left];
        for (;begin < end;) {
            for (; begin < end && array[begin] < split; ) {
                begin++;
            }
            for (; begin < end && array[end] >= split; ) {
                end--;
            }
            if (begin < end) {
                Utils.swap(array, begin, end);
            }
        }
        Utils.swap(array, left, end);
        return end;
    }

    /**
     * 标准值放右边， 先从右边开始
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private static int rightFirstStandardRight(int[] array, int begin, int end) {
        int left = end;
        int split = array[left];
        for (;begin < end;) {
            for (; begin < end && array[end] >= split; ) {
                end--;
            }
            for (; begin < end && array[begin] < split; ) {
                begin++;
            }
            if (begin < end) {
                Utils.swap(array, begin, end);
            }
        }
        // 从小到大排序， 先左后右 需对分界值做判断, 会出现分界值小于标准值的情况
        if (array[left] > array[end]) {
            end++;
        }
        Utils.swap(array, left, end);
        return end;
    }

    /**
     * 逆序
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private static int desc(int[] array, int begin, int end) {
        // TODO
        return end;
    }
}
