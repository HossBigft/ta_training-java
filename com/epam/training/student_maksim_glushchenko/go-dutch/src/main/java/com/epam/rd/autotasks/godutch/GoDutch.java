package com.epam.rd.autotasks.godutch;

import java.util.Scanner;

public class GoDutch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalBill = sc.nextInt();
        if(totalBill<0){
            System.out.println("Bill total amount cannot be negative");
            return;
        }
        totalBill*=1.1;
        int numberOfFriends = sc.nextInt();
        if(numberOfFriends<=0){
            System.out.println("Number of friends cannot be negative or zero");
            return;
        }
        String actual = Integer.toString( totalBill/numberOfFriends);
        System.out.println(actual);
    }
}
