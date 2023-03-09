package com.epam.rd.autotasks.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {
    public static void main(String[] args) {
        //Write a program, reading number of people and number of pieces per pizza and then
        //printing minimum number of pizzas to order to split all the pizzas equally and with no remainder
        Scanner sc = new Scanner(System.in);
        int numberOfPeople = sc.nextInt();
        int numberOfSlices = sc.nextInt();
        int numberOfPizzas = 0;
        while(true){
            numberOfPizzas++;
            if((numberOfPizzas*numberOfSlices)%numberOfPeople==0) break;
        }
        System.out.println(numberOfPizzas);


    }
}
