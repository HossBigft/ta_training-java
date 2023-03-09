package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{10, 900};

        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array){
        int[] copy = Arrays.copyOf(array, array.length);
        int resultArrLen = copy.length;
        if(resultArrLen==2){
            if(copy[1]>copy[0]){
                resultArrLen--;
                int[] result = new int[resultArrLen];
                System.arraycopy(copy, 0, result, 0, resultArrLen);
                return result;
            }
        }
        for(int i =0; i<array.length-1;i++){
            if(i==0){
                if(copy[i]>copy[i+1]) {
                    System.arraycopy(copy, 1, copy, 0, copy.length - 1);
                    copy[copy.length - 1] = 0;
                    resultArrLen--;
                }
            }
            if(i==copy.length-1){
                if( copy[i-1]>copy[i-2]){
                    System.arraycopy(copy, 1, copy, 0, copy.length - 1);
                    copy[copy.length - 1] = 0;
                    resultArrLen--;
                }
            }

            if(i>0){
                if( copy[i]>copy[i+1] & copy[i]>copy[i-1]) {
                    for (int j = i; j < copy.length-1; j++) {
                        copy[j] = copy[j + 1];
                    }
                    copy[copy.length - 1] = 0;
                    resultArrLen--;
                }
            }
        }
        int[] result = new int[resultArrLen];
        System.arraycopy(copy, 0, result, 0, resultArrLen);
        return result;

    }
}
