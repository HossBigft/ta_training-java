package com.epam.rd.autotasks.snail;
import java.util.Scanner;

public class Snail
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        int snailSpeed = sc.nextInt();
        int snailSlideSpeed = sc.nextInt();
        int treeHeight = sc.nextInt();
        int days = 0;
        int current=0;
        while(true){
            current+=snailSpeed;
            days++;

            if(current>=treeHeight){
                break;
            }

            current-=snailSlideSpeed;
            if(current<=0){
                System.out.println("Impossible");
                return;
            }
        }
        System.out.println(days);
    }
}
