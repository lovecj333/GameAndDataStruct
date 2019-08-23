package com.data.struct.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        //int[] arr = {101, 34, 118, 1};
        int[] arr = new int[800];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        long n1 = System.currentTimeMillis();
        insertSort(arr);
        long n2 = System.currentTimeMillis();
        System.out.println(n2 - n1);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr){

        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];    //无序列表待插入的数
            int sortLastIndex = i - 1;    //有序列表最后一个数的下标
            //给insertValue找到插入的位置 sortLastIndex >= 0 保证往前比较数组不会越界
            //insertValue比前面的数小前面的数就得往后挪一位
            while(sortLastIndex >= 0 && insertValue < arr[sortLastIndex]){
                arr[sortLastIndex + 1] = arr[sortLastIndex];
                sortLastIndex--;
            }
            if(sortLastIndex + 1 != i){
                arr[sortLastIndex + 1] = insertValue;
            }
        }

//        //第1轮   {101, 34, 118, 1} => {34, 101, 118, 1}
//        int insertValue = arr[1];    //无序列表待插入的数
//        int sortLastIndex = 1 - 1;    //有序列表最后一个数的下标
//        //给insertValue找到插入的位置 sortLastIndex >= 0 保证往前比较数组不会越界
//        //insertValue比前面的数小前面的数就得往后挪一位
//        while(sortLastIndex >= 0 && insertValue < arr[sortLastIndex]){
//            arr[sortLastIndex + 1] = arr[sortLastIndex];
//            sortLastIndex--;
//        }
//        arr[sortLastIndex + 1] = insertValue;
//        //第2轮   {34, 101, 118, 1} => {34, 101, 118, 1}
//        insertValue = arr[2];    //无序列表待插入的数
//        sortLastIndex = 2 - 1;    //有序列表最后一个数的下标
//        //给insertValue找到插入的位置 sortLastIndex >= 0 保证往前比较数组不会越界
//        //insertValue比前面的数小前面的数就得往后挪一位
//        while(sortLastIndex >= 0 && insertValue < arr[sortLastIndex]){
//            arr[sortLastIndex + 1] = arr[sortLastIndex];
//            sortLastIndex--;
//        }
//        arr[sortLastIndex + 1] = insertValue;
//        //第3轮   {34, 101, 118, 1} => {1, 34, 101, 118}
//        insertValue = arr[3];    //无序列表待插入的数
//        sortLastIndex = 3 - 1;    //有序列表最后一个数的下标
//        //给insertValue找到插入的位置 sortLastIndex >= 0 保证往前比较数组不会越界
//        //insertValue比前面的数小前面的数就得往后挪一位
//        while(sortLastIndex >= 0 && insertValue < arr[sortLastIndex]){
//            arr[sortLastIndex + 1] = arr[sortLastIndex];
//            sortLastIndex--;
//        }
//        arr[sortLastIndex + 1] = insertValue;
    }
}
