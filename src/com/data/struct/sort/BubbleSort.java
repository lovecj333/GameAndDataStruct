package com.data.struct.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        //int[] arr = {3, 9, 1, 10, 2};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        long n1 = System.currentTimeMillis();
        bubbleSort(arr);
        long n2 = System.currentTimeMillis();
        System.out.println(n2 - n1);
        //System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSwap = false; //标识是否进行过交换
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]){
                    isSwap = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!isSwap){    //没有进行过交换就说明已经有顺序了
                break;
            }
        }
    }
}
