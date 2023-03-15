package Planes;

import java.util.Objects;

public class PassengerPlane extends Plane{

    //=================FIELDS=================
    private int passengersCapacity;

    //=================CONSTRUCTORS=================
    public PassengerPlane(PlaneEntry data, int passengersCapacity) {
        super(data);
        this.passengersCapacity = passengersCapacity;
    }


    //=================METHODS=================
    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity=" + passengersCapacity +
                '}');
    }


    @Override
    public boolean equals(Object compared) {
        if (this == compared) return true;
        if (!(compared instanceof PassengerPlane)) return false;
        if (!super.equals(compared)) return false;
        PassengerPlane plane = (PassengerPlane) compared;
        return passengersCapacity == plane.passengersCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }

    @Override
    public void accept(PlaneVisitor v){
        v.visit(this);
    }
}
