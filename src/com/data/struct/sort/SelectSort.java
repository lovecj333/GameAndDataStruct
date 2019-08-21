package com.data.struct.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {
        //int[] arr = {101, 34, 118, 1};
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        long n1 = System.currentTimeMillis();
        selectSort(arr);
        long n2 = System.currentTimeMillis();
        System.out.println(n2 - n1);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
