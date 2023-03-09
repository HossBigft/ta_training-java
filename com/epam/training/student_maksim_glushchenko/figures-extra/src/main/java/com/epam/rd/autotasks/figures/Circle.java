package com.epam.rd.autotasks.figures;

class Circle extends Figure{
    private  final Point center;
    private final double radius;
    public  Circle(Point center, double radius){
        if(radius<=0 || center==null){
            throw new IllegalArgumentException("Invalid circle");
        }
        this.center=center;
        this.radius=radius;
    }


    public double area() {
        return radius*radius*3.141592653589793;
    }

    @Override
    public String toString() {
        return "Circle["+center.toString()+radius+"]";
    }

    public String pointsToString() {
        return center.toString();
    }

    public Point leftmostPoint() {
        return new Point(center.getX()-radius,center.getY());
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if(this==figure){
            return true;
        }
        if(!(figure instanceof Circle)){
            return false;
        }
        Circle comparedCircle = (Circle) figure;
        if(fuzzyEqual(this.getRadius(), comparedCircle.getRadius())
            && fuzzyEqual(this.center.getX(),comparedCircle.center.getX())
            && fuzzyEqual(this.center.getY(), comparedCircle.center.getY())){
            return true;
        }
        return false;
    }
    public double getRadius(){
        return radius;
    }

    @Override
    public Point centroid() {
        return center;
    }

    private boolean fuzzyEqual(double desiredValue, double actualValue) {
        double diff = Math.abs(desiredValue - actualValue);
        double tolerance = 0.01;
        return diff <= tolerance;
    }
}
