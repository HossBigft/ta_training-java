package com.epam.rd.autotasks.meetanagent;

import java.util.Scanner;

public class MeetAnAgent {
    final static int PASSWORD = 133976; //You can change pass, but don't change the type

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pass = sc.nextInt();
        if(pass==PASSWORD){
            System.out.printf("Hello, Agent");
        } else{
            System.out.printf("Access denied");
        }
    }
}
