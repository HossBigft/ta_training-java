package com.epam.rd.autotasks.segments;

import static java.lang.Math.*;
import static java.lang.StrictMath.pow;

class Segment {
    private  Point start;
    private Point end;
    public Segment(Point start, Point end) {
        if(start.getX()==end.getX() & start.getY()==end.getY()){
            throw new IllegalArgumentException();
        }
        this.start=start;
        this.end=end;
    }

    double length() {
        double length = sqrt(pow(start.getX()- end.getX(),2)+pow(start.getY()- end.getY(),2));
        return length;
    }

    Point middle() {
        double middleX = (start.getX() + end.getX())/2;
        double middleY = (start.getY() + end.getY())/2;
        return  new Point(middleX, middleY);
    }

    Point intersection(Segment another) {
        double x1= this.start.getX();
        double x2= this.end.getX();
        double y1= this.start.getY();
        double y2= this.end.getY();

        double x3= another.start.getX();
        double x4= another.end.getX();
        double y3= another.start.getY();
        double y4= another.end.getY();

        double a1 = y2- y1;
        double b1 = x1 - x2;
        double c1 = a1*(x1) + b1*(y1);

        // Line CD represented as a2x + b2y = c2
        double a2 = y4 - y3;
        double b2 = x3 - x4;
        double c2 = a2*(x3)+ b2*(y3);
        if(a1==a2){
            return  null;
        }
        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0)
        {
            return null;
        }
        else
        {
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;
            if((x>=min(x1, x2) && x<=max(x3, x4)) && (y>=min(y1, y2) && y<=max(y3, y4))) {
                return new Point(x, y);
            }else{
                return null;
            }

        }

    }

}
