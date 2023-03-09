package com.epam.rd.autotasks.figures;

class Circle extends Figure{
    private  final Point center;
    private final double radius;
    public  Circle(Point center, double radius){
        if(radius<=0){
            throw new IllegalArgumentException("Radius must be greater than zero");
        }
        this.center=center;
        this.radius=radius;
    }

    @Override
    public double area() {
        return radius*radius*3.141592653589793;
    }

    @Override
    public String toString() {
        return "Circle["+center.toString()+radius+"]";
    }
    @Override
    public String pointsToString() {
        return center.toString();
    }

    @Override
    public Point leftmostPoint() {
        return new Point(center.getX()-radius,center.getY());
    }
}
