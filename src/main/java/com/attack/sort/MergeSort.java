package com.attack.sort;

import java.util.Arrays;

/**
 * Create by zhang on 2017/12/15
 * 归并排序
 */
public class MergeSort {

    /**
     * 将两个数组进行归并，归并前面2个数组已有序，归并后依然有序
     * @param a
     *            数组对象
     * @param low
     *            左数组的第一个元素的索引
     * @param mid
     *            左数组的最后一个元素的索引，mid+1是右数组第一个元素的索引
     * @param high
     *            右数组最后一个元素的索引
     */
    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[k2 + low] = temp[k2];
        }
    }

    public static void mergeSort(int[] a, int low, int high) {
        System.out.println("low:"+low+",high:"+high);
        int mid =  low + (high - low)/2;
        if (low < high) {
            // 左边
            System.out.println("左边merge");
            mergeSort(a, low, mid);
            // 右边
            System.out.println("右边merge");
            mergeSort(a, mid + 1, high);
            // 左右归并
            merge(a, low, mid, high);
            System.out.println(Arrays.toString(a));
        }

    }

    public static void main(String[] args) {
        int a[] = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50 };
        mergeSort(a, 0, a.length - 1);
        System.out.println("排序结果：" + Arrays.toString(a));
    }
}
