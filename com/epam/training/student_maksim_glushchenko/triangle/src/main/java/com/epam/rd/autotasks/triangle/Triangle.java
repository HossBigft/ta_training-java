package com.epam.rd.autotasks.triangle;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Triangle {
    private Point a,b,c;
    public Triangle(Point a, Point b, Point c) {
        double sideA = length(a,b);
        double sideB = length(b,c);
        double sideC = length(c,a);
        if(sideA+sideB<=sideC || sideB+sideC<=sideA || sideA+sideC<=sideB){
            throw new IllegalArgumentException();
        }
        this.a=a;
        this.b=b;
        this.c=c;
    }
    double length(Point start, Point end) {
        double length = sqrt(pow(start.getX()- end.getX(),2)+pow(start.getY()- end.getY(),2));
        return length;
    }
    public double area() {
        double sideA = length(a,b);
        double sideB = length(b,c);
        double sideC = length(c,a);
        double semiPerimeter = (sideA +sideB+sideC)/2;
        double Area = sqrt(semiPerimeter*(semiPerimeter-sideA)* (semiPerimeter-sideB) * (semiPerimeter-sideC));
        return Area;
    }

    public Point centroid(){
        double x = (a.getX()+b.getX()+c.getX())/3;
        double y = (a.getY()+b.getY()+c.getY())/3;
        return  new Point(x,y);
    }

}
