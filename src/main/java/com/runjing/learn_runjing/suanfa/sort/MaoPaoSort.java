package com.runjing.learn_runjing.suanfa.sort;

/**
 * @author : spring
 * {@code @description:}
 * {@code @date} : 2024/4/3
 * {@code @modified} By: spring
 * {@code @project:} learn_runjing
 */
public class MaoPaoSort {
    public static void maoPaoSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swapPlus(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4};
        maoPaoSort(arr);
        for (int j : arr) {
            System.out.print(j);
        }
    }
}
