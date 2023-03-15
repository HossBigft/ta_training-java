package Planes;

import models.MilitaryType;

import java.util.*;

public class PlaneManager{
    private List<PassengerPlane> passengerPlanesList= new ArrayList<>();
    private List<MilitaryPlane> militaryPlanesList= new ArrayList<>();
    private List<ExperimentalPlane> experimentalPlanesList = new ArrayList<>();
    private final AddPlaneVisitor addVisitor = new AddPlaneVisitor(this);


    public  void add(Plane plane) {
        plane.accept(addVisitor);
    }
    public PlaneManager addAll(List<? extends Plane> plane){
        for(Plane p: plane){
            add(p);
        }
        return this;
    }

    public void add(PassengerPlane plane){
        passengerPlanesList.add(plane);
    }
    public void add(MilitaryPlane plane){
        militaryPlanesList.add(plane);
    }
    public void add(ExperimentalPlane plane){
        experimentalPlanesList.add(plane);
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return passengerPlanesList;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return militaryPlanesList;
    }
    public List<ExperimentalPlane> getExperimentalPlanes() {

        return experimentalPlanesList;
    }
    public List<Plane> getPlanes() {
        List<Plane> planesList = new ArrayList<>();
        planesList.addAll(militaryPlanesList);
        planesList.addAll(passengerPlanesList);
        planesList.addAll(experimentalPlanesList);
        return planesList;
    }


    public PassengerPlane getPlaneWithMaxPassengersCapacity() {
        PassengerPlane planeWithMaxCapacity = passengerPlanesList.get(0);
        for (PassengerPlane passengerPlane : passengerPlanesList) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }

        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType type) {
        List<MilitaryPlane> planesOfType= new ArrayList<>();
        for (MilitaryPlane currPlane : militaryPlanesList) {
            if (currPlane.getType() == type) {
                planesOfType.add(currPlane);
            }
        }
        if(planesOfType.isEmpty()){
            return null;
        }
        return  planesOfType;
    }



    public PlaneManager sortByMaxDistance() {
        List<Plane> planes = getPlanes();

        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
        return this;
    }

    public List<Plane> getPlanesSortedByMaxSpeed() {
        List<Plane> planes = getPlanes();
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
        return planes;
    }

    public List<Plane> getPlanesSortedByMaxLoadCapacity() {
        List<Plane> planes = getPlanes();
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
        return planes;
    }



    private void print(Collection<? extends Plane> collection) {
        for (Plane plane : collection) {
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + getPlanes().toString() +
                '}';
    }


}
