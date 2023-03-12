package Planes;

public class PlaneEntry {

    String model;
    private final int  maxSpeed;
    private final int maxFlightDistance;
    private  final int maxLoadCapacity;
    private PlaneEntry(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        if (model == null || model.equals("") || maxSpeed == 0 || maxFlightDistance == 0 || maxLoadCapacity == 0) {
            throw new IllegalArgumentException();
        }
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return maxLoadCapacity;
    }
}
