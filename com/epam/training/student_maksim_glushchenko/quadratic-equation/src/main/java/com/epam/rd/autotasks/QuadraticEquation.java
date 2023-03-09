package com.epam.rd.autotasks;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double discriminant = sqrt(b*b-4*a*c);
        double x1;
        double x2;
        if(discriminant!=discriminant){
            System.out.println("no roots");
            return;
        }

        if(discriminant==0){
            x1=-b/2*a;
            System.out.printf(Double.toString(x1));
        } else{
            x1=(-b+discriminant)/(2*a);
            x2=(-b-discriminant)/(2*a);
            System.out.println(x1 + " " + x2);
        }
    }

}