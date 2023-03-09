package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private final Point a,b,c;
    public Triangle(Point a, Point b, Point c){
        if( a==null || b==null || c==null){
            throw new IllegalArgumentException("One or all points are null");
        }
        this.a=a;
        this.b=b;
        this.c=c;
        area();
    }
    public double area() {
        double ax= a.getX();
        double ay= a.getY();
        double bx= b.getX();
        double by= b.getY();
        double cx= c.getX();
        double cy = c.getY();
        double area= Math.abs(ax*(by-cy)+bx*(cy-ay)+cx*(ay-by))/2;
        if(area<=0){
            throw  new IllegalArgumentException();
        }
        return  area;
    }

    private double vectorMagnitude(Point a, Point b){
        double x1=a.getX();
        double y1=a.getY();
        double x2=b.getX();
        double y2=b.getY();
        double magnitude= Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2 - y1), 2));
        return magnitude;
    }

    public Point centroid(){
        double x=(a.getX()+b.getX()+c.getX())/3;
        double y=(a.getY()+b.getY()+c.getY())/3;
        return new Point(x,y);
    }
    public boolean isTheSame(Figure figure){
        if(this==figure){
            return true;
        }
        if(!(figure instanceof Triangle)){
            return false;
        }
        Triangle compared= (Triangle) figure;
        return  fuzzyEqual(this.vectorMagnitude(a, b),compared.vectorMagnitude(a, b))
                && fuzzyEqual(this.vectorMagnitude(b, c),compared.vectorMagnitude(b, c))
                && fuzzyEqual(this.vectorMagnitude(c, a),compared.vectorMagnitude(c, a));
    }
    private boolean fuzzyEqual(double desiredValue, double actualValue) {
        double diff = Math.abs(desiredValue - actualValue);
        double tolerance = 0.01;
        return diff <= tolerance;
    }
}
