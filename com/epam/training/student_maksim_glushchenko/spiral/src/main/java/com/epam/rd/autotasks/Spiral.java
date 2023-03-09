package com.epam.rd.autotasks;

import java.util.Arrays;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        int rowStart=0;
        int rowEnd=rows-1;
        int colStart=0;
        int colEnd=columns-1;
        int counter =1;
        while(rowStart<=rowEnd && colStart<=colEnd){
            for(int i = colStart; i <= colEnd; i++){
                arr[rowStart][i]=counter;
                counter++;
            }
            rowStart++;

            for(int i = rowStart; i <= rowEnd; i++){
                arr[i][colEnd]=counter;
                counter++;
            }
            colEnd--;
            if(rowStart <= rowEnd){
                for(int i = colEnd; i >= colStart; i--){
                    arr[rowEnd][i]=counter;
                    counter++;
                }
                rowEnd--;
            }
            if(colStart <= colEnd){
                for(int i = rowEnd; i >= rowStart; i--){
                    arr[i][colStart]=counter;
                    counter++;
                }
                colStart++;
            }
        }
        return arr;
    }
    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(spiral(3, 9)));
    }
}
