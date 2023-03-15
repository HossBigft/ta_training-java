import Planes.*;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PlaneManagerTest {
    private final static List<Plane> planes = Arrays.asList(
            new PassengerPlane(new PlaneEntry("Boeing-737", 900, 12000, 60500), 164),
            new PassengerPlane(new PlaneEntry("Boeing-737-800", 940, 12300, 63870), 192),
            new PassengerPlane(new PlaneEntry("Boeing-747", 980, 16100, 70500), 242),
            new PassengerPlane(new PlaneEntry("Airbus A320", 930, 11800, 65500), 188),
            new PassengerPlane(new PlaneEntry("Airbus A330", 990, 14800, 80500), 222),
            new PassengerPlane(new PlaneEntry("Embraer 190", 870, 8100, 30800), 64),
            new PassengerPlane(new PlaneEntry("Sukhoi Superjet 100", 870, 11500, 50500), 140),
            new PassengerPlane(new PlaneEntry("Bombardier CS300", 920, 11000, 60700), 196),
            new MilitaryPlane(new PlaneEntry("B-1B Lancer", 1050, 21000, 80000), MilitaryType.BOMBER),
            new MilitaryPlane(new PlaneEntry("B-2 Spirit", 1030, 22000, 70000), MilitaryType.BOMBER),
            new MilitaryPlane(new PlaneEntry("B-52 Stratofortress", 1000, 20000, 80000), MilitaryType.BOMBER),
            new MilitaryPlane(new PlaneEntry("F-15", 1500, 12000, 10000), MilitaryType.FIGHTER),
            new MilitaryPlane(new PlaneEntry("F-22", 1550, 13000, 11000), MilitaryType.FIGHTER),
            new MilitaryPlane(new PlaneEntry("C-130 Hercules", 650, 5000, 110000), MilitaryType.TRANSPORT),
            new ExperimentalPlane(new PlaneEntry("Bell X-14", 277, 482, 500), ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane(new PlaneEntry("Ryan X-13 Vertijet", 560, 307, 500), ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private final static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane(new PlaneEntry("Boeing-747", 980, 16100, 70500), 242);

    @Test
    public void testGetTransportMilitaryPlanes() {
        PlaneManager planeManager = new PlaneManager().addAll(planes);

        PlaneManager militaryPlaneManager = new PlaneManager();
        militaryPlaneManager.addAll(planeManager.getMilitaryPlanes());

        PlaneManager passengerPlaneManager = new PlaneManager();
        passengerPlaneManager.addAll(planeManager.getPassengerPlanes());

        List<MilitaryPlane> transportMilitaryPlanes = planeManager.getMilitaryPlanesByType(MilitaryType.TRANSPORT);
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            Assert.assertSame(militaryPlane.getType(), MilitaryType.TRANSPORT);
        }

    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        PlaneManager planeManager = new PlaneManager().addAll(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = planeManager.getPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(planeWithMaxPassengerCapacity, expectedPlaneWithMaxPassengersCapacity);
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        PlaneManager planeManager = new PlaneManager().addAll(planes);
        List<? extends Plane> planesSortedByMaxLoadCapacity = planeManager.getPlanesSortedByMaxLoadCapacity();

        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            Assert.assertFalse(currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity());
        }

    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        PlaneManager planeManager = new PlaneManager().addAll(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = planeManager.getMilitaryPlanesByType(MilitaryType.BOMBER);
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            Assert.assertSame(militaryPlane.getType(), MilitaryType.BOMBER);
        }

    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
        PlaneManager planeManager = new PlaneManager().addAll(planes);
        List<ExperimentalPlane> ExperimentalPlanes = planeManager.getExperimentalPlanes();
        for(ExperimentalPlane experimentalPlane : ExperimentalPlanes){
            Assert.assertNotSame(experimentalPlane.getClassificationLevel(), ClassificationLevel.UNCLASSIFIED);
        }
    }
}
