package com.runjing.learn_runjing.suanfa.sort;

/**
 * @author : spring
 * {@code @description:}
 * {@code @date} : 2024/4/3
 * {@code @modified} By: spring
 * {@code @project:} learn_runjing
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 找到最小的数
                minIndex = arr[j] > arr[minIndex] ? minIndex : j;
            }
            // 交换位置
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 2, 4};
        selectSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }
}
