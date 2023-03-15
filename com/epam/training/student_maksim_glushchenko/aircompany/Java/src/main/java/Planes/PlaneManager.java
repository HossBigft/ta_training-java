package Planes;

import models.MilitaryType;

import java.util.*;

public class PlaneManager{
    private List<PassengerPlane> passengerPlanesList= new ArrayList<>();
    private List<MilitaryPlane> militaryPlanesList= new ArrayList<>();
    private List<ExperimentalPlane> experimentalPlanesList = new ArrayList<>();
    private  AddPlaneVisitor addVisitor = new AddPlaneVisitor(this);


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


    public PassengerPlane getPlaneWithMostPassengersAboard() {
        PassengerPlane planeWithMaxCapacity = passengerPlanesList.get(0);
        for (int i = 0; i < passengerPlanesList.size(); i++) {
            if (passengerPlanesList.get(i).getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlanesList.get(i);
            }
        }

        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType type) {
        List<MilitaryPlane> planesOfType= new ArrayList<>();
        for(int i=0; i<militaryPlanesList.size(); i++){
            MilitaryPlane currPlane = militaryPlanesList.get(i);
            if(currPlane.getType()==type){
                planesOfType.add(currPlane);
            }
        }
        if(planesOfType.isEmpty()){
            return null;
        }
        return  planesOfType;
    }



    public List<Plane> sortByMaxDistance() {
        List<Plane> planes = getPlanes();

        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxFlightDistance() - o2.getMaxFlightDistance();
            }
        });
        return planes;
    }

    public PlaneManager sortByMaxSpeed() {
        List<Plane> planes = getPlanes();
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxSpeed() - o2.getMaxSpeed();
            }
        });
        return this;
    }

    public PlaneManager sortByMaxLoadCapacity() {
        List<Plane> planes = getPlanes();
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxLoadCapacity() - o2.getMaxLoadCapacity();
            }
        });
        return this;
    }



    private void print(Collection<? extends Plane> collection) {
        Iterator<? extends Plane> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
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
