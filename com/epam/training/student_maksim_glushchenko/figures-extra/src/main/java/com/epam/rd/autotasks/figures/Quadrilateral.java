package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure{
    private final Point a,b,c,d;

    public Quadrilateral(Point a, Point b, Point c, Point d){
        if(a==null || b==null || c==null || d==null){
            throw new IllegalArgumentException("One or all points are null");
        }
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        checkTriangles();
        isValid();
    }

    private void checkTriangles() {
        new Triangle(a, b, c);
        new Triangle(b, c, d);
        new Triangle(c, d, a);
        new Triangle(d, a, b);
    }

    @Override
    public Point centroid() {
        Point c1=new Triangle(a,b,c).centroid();
        Point c2=new Triangle(b,c,d).centroid();
        Point c3=new Triangle(c,d,a).centroid();
        Point c4=new Triangle(d,a,b).centroid();
        Point centroid = intersection(c1,c3,c2,c4);
        return centroid;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if(this==figure){
            return true;
        }
        if(!(figure instanceof Quadrilateral)){
            return false;
        }
        Quadrilateral compared= (Quadrilateral) figure;
        return this.area()==compared.area();
    }

    private double vectorMagnitude(Point a, Point b){
        double x1=a.getX();
        double y1=a.getY();
        double x2=b.getX();
        double y2=b.getY();
        double magnitude= Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2 - y1), 2));
        return magnitude;
    }

    public void isValid(){
        double area =area();
        if (a.getX() == c.getX()
                || a.getY() == c.getY() ||
                b.getX() == d.getX()
                && b.getY() == d.getY()||
                d.getX()==a.getX() && d.getY()==a.getY()) {
            throw new IllegalArgumentException();
        }


        if(area<=0 || intersection(a,c,b,d)==null  ){
            throw new IllegalArgumentException();
        }
    }

    private double lineAngle(double slope1, double slope2){
        double angle = Math.abs((slope2 - slope1) / (1 + slope1 * slope2));

        // Calculate tan inverse of the angle
        double ret = Math.atan(angle);

        // Convert the angle from
        // radian to degree
        double val = (ret * 180) / 3.141592653589793;
        return  val;
    }

    Point middle(Point head, Point tail) {
        double middleX = (head.getX() + tail.getX())/2;
        double middleY = (head.getY() + tail.getY())/2;
        return  new Point(middleX, middleY);
    }
    private double slope(Point h, Point t){
        double slope=(h.getY()-t.getY())/(h.getX()-t.getX());
        return slope;
    }
    private  double dotProduct(Point h1, Point h2){
        return h1.getX()*h2.getX()+h1.getY()* h2.getY();
    }
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

    Point intersection(Point h1, Point t1, Point h2, Point t2) {
        double x1= h1.getX();
        double x2= t1.getX();
        double y1= h1.getY();
        double y2= t1.getY();

        double x3= h2.getX();
        double x4= t2.getX();
        double y3= h2.getY();
        double y4= t2.getY();


        double dividendT = determinant2x2(new double[][]{
                {x1 - x3, x3 - x4},
                {y1 - y3, y3 - y4}
        });
        double divisorT = determinant2x2(new double[][]{
                {x1 - x2, x3 - x4},
                {y1 - y2, y3 - y4}
        });
        double dividendU = determinant2x2(new double[][]{
                {x1 - x3, x1 - x2},
                {y1 - y3, y1 - y2}
        });
        double divisorU = determinant2x2(new double[][]{
                {x1 - x2, x3 - x4},
                {y1 - y2, y3 - y4}
        });

        double t = dividendT / divisorT;
        double u = dividendU / divisorU;

        if (!(t >= 0 && t <= 1 && u >= 0 && u <= 1)) {
            return null;
        }

        return new Point(x1 + t * (x2 - x1), y1 + t * (y2 - y1));

    }
    private double determinant2x2(double[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }
}
