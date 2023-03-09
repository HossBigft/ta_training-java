package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int counter = 0;
        while(scanner.hasNextInt()){
            int input = scanner.nextInt();
            if(input==0)break;
            sum+=input;
            counter++;
        }
        System.out.println(sum/counter);
    }

}