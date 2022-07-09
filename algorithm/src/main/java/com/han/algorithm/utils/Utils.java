package com.han.algorithm.utils;

public class Utils {

    /**
     * 生成begin 与end之间的 长度为 length 数组
     * @param length
     * @param begin
     * @param end
     * @return
     */
    public static int[] generateRandowArray(int length, int begin, int end) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            int random = (int) Math.round(Math.random() * end);
            if (random < begin) {
                random += begin;
            }
            array[i] = random;
        }
        return array;
    }
    /**
     * 将j -- i-1 的数往后移一位， 将原来i上的值放到j上
     *
     * @param array
     * @param i
     * @param j
     */
    public static void insert(int[] array, int i, int j) {
        int origin = array[i];
        for (int k = i; k > j; k--) {
            array[k] = array[k-1];
        }
        array[j] = origin;
    }

    /**
     * 交换数组i、j位置上的值
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int origin = array[i];
        array[i] = array[j];
        array[j] = origin;
    }
}
