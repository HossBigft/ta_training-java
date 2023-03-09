package com.epam.rd.autotasks.max;

public class MaxMethod {
    public static int max(int[] values) {
        int max=0;
        for(int i=0; i<values.length; i++){
            if(max==0){
                max=values[i];
            } else if(max<values[i]){
                max=values[i];
            }
        }
        return max;
    }
}
