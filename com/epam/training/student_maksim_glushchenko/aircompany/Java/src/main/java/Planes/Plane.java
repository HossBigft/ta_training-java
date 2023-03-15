package Planes;

public abstract class Plane{
    PlaneEntry data;

    public Plane(PlaneEntry entry) {
        this.data=entry;
    }

    public String getModel() {return data.getModel();}

    public int getMaxSpeed() {return data.getMaxSpeed();}

    public int getMaxFlightDistance() {return data.getMaxFlightDistance();}

    public int getMaxLoadCapacity() {return data.getMaxLoadCapacity();}

    @Override
    public String toString() {
        return "Plane{" +
                "model='" + getModel() + '\'' +
                ", maxSpeed=" + getMaxSpeed() +
                ", maxFlightDistance=" + getMaxFlightDistance() +
                ", maxLoadCapacity=" + getMaxLoadCapacity() +
                '}';
    }

    @Override
    public boolean equals(Object compared) {
        if (this == compared) return true;
        if (!(compared instanceof Plane)) return false;
        Plane comparedPlane = (Plane) compared;
        return this.getMaxSpeed() == comparedPlane.getMaxSpeed() &&
                this.getMaxFlightDistance() == comparedPlane.getMaxFlightDistance() &&
                this.getMaxLoadCapacity() == comparedPlane.getMaxLoadCapacity() &&
                this.getModel().equals(comparedPlane.getModel());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime + result + getMaxSpeed() + getMaxFlightDistance() +getMaxLoadCapacity() +
                 getModel().hashCode();

        return result;
    }
    public abstract void accept(PlaneVisitor v);
}
