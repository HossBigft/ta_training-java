package Planes;

public class AddPlaneVisitor implements PlaneVisitor {
      private PlaneManager manager;
      public  AddPlaneVisitor(PlaneManager manager){
            this.manager=manager;
      }
      public void  visit(PassengerPlane plane){
            manager.add(plane);
      };
      public void visit(MilitaryPlane  plane){
            manager.add(plane);
      };
      public void visit(ExperimentalPlane  plane){
            manager.add(plane);
      };

}
