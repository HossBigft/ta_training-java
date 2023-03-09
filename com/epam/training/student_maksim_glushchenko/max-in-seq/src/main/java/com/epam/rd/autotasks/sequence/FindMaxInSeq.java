package com.epam.rd.autotasks.sequence;
import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {

        Scanner sc = new Scanner(System.in);
        int max = 0;
        int counter =0;
        while(sc.hasNextInt()){

            int input = sc.nextInt();
            if(counter==0)max=input;
            counter++;
            if(input==0)break;
            if(max<input){
                max= input;
            }
        }

        return max;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        System.out.println(max());
    }
}
