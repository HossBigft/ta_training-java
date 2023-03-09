package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {
    private final Point a,b,c,d;
     public Quadrilateral(Point a, Point b, Point c, Point d){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
     }

    @Override
    public double area() {
        double area= 0.5 * ((a.getX()*b.getY()
                + b.getX()*c.getY()
                + c.getX()*d.getY()
                + d.getX()*a.getY())-
                (b.getX()*a.getY()
                        + c.getX()*b.getY()
                        + d.getX()*c.getY()
                        + a.getX()*d.getY()));
        return  Math.abs(area);

    }
    private double vectorMagnitude(Point a, Point b){
         double x1=a.getX();
         double y1=a.getY();
         double x2=b.getX();
         double y2=b.getY();
         double magnitude= Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2 - y1), 2));
         return magnitude;
    }
    private  double halfPerimeter(){
         double halfPerimeter= (vectorMagnitude(a,b)+vectorMagnitude(b,c)+vectorMagnitude(c,d)+vectorMagnitude(d,a))/2.0;
         return halfPerimeter;
    }

    @Override
    public Point leftmostPoint() {
        Point[] points= {a,b,c,d};
        Point leftmost = a;
        for(Point point : points){
            if(point.getX()< leftmost.getX()){
                leftmost=point;
            }
        }
        return leftmost;
    }

    @Override
    public String pointsToString() {
        return ""+a.toString()+b.toString()+c.toString()+d.toString();
    }
}
