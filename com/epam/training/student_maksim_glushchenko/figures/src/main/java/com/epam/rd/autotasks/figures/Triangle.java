package com.epam.rd.autotasks.figures;

import static java.lang.Math.abs;

class Triangle extends Figure{
    private final Point a,b,c;

    public Triangle (Point a, Point b, Point c){
        if(area(a,b,c)==0){
            throw new IllegalArgumentException("Area of com.epam.training.student_maksim_glushchenko.triangle must be greater than zero");
        }
        this.a=a;
        this.b=b;
        this.c=c;
    }

    @Override
    public String pointsToString() {
        return a.toString()+b.toString()+c.toString();
    }

    @Override
    public Point leftmostPoint() {
        Point leftmost = a;
        if(b.getX()<leftmost.getX()){
            leftmost =b;
        }
        if (c.getX()<leftmost.getX()) {
            leftmost=c;
        }
        return leftmost;
    }

    @Override
    public double area() {
        double ax= a.getX();
        double ay= a.getY();
        double bx= b.getX();
        double by= b.getY();
        double cx= c.getX();
        double cy = c.getY();
        return abs(ax*(by-cy)+bx*(cy-ay)+cx*(ay-by))/2;
    }
    public double area(Point a, Point b, Point c) {
        double ax= a.getX();
        double ay= a.getY();
        double bx= b.getX();
        double by= b.getY();
        double cx= c.getX();
        double cy = c.getY();
        return abs(ax*(by-cy)+bx*(cy-ay)+cx*(ay-by))/2;
    }
}
