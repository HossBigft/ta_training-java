package com.epam.rd.autotasks;

import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {
     for(int i=array.length-1; i>0; i--){
         int tmp;
         tmp=array[i];
         array[i]=array[i-1];
         array[i-1]=tmp;
     }
    }

    static void cycleSwap(int[] array, int shift) {
        for(int i=0; i<shift;i++){
            cycleSwap(array);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{ 1, 3, 2, 7, 4};
        CycleSwap.cycleSwap(array, 2);
        System.out.println(Arrays.toString(array));
    }
}
