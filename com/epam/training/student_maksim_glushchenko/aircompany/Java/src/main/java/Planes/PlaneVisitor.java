package Planes;

interface PlaneVisitor {
    void  visit(PassengerPlane plane);
    void visit(MilitaryPlane  plane);
    void visit(ExperimentalPlane  plane);
}
