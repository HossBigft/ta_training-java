package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        int hours = seconds/(60*60)%24;
        int minutes = (seconds/60 - hours*60)%60;
        int secsPerH= (seconds -(hours*60*60 + minutes*60))%60;
        String output= hours+ ":";
        if(minutes>9){
            output+=minutes+":";
        }else if(minutes<10&minutes>0){
            output+="0"+minutes+":";
        }else{
            output+="00:";
        }
        if(secsPerH>9){
            output+=secsPerH;
        }else if(secsPerH<10&secsPerH>0){
            output+="0"+secsPerH;
        }else{
            output+="00";
        }
        System.out.printf(output);
    }
}
