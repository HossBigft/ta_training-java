package com.epam.rd.autotasks.meetstrangers;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        int count = new Scanner(System.in).nextInt();
        if(count==0){
            System.out.println("Oh, it looks like there is no one here");
            return;
        } else if(count<0){
            System.out.println("Seriously? Why so negative?");
            return;
        }
        String [] names= new String[count];
        for(int i=0; i<count; i++){
            names[i]=new Scanner(System.in).nextLine();
        }
        for(int i=0; i<count; i++){
            System.out.println("Hello, "+names[i]);
        }
    }
}
