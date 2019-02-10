package studentOrientation.driver;

import studentOrientation.util.*;

public class Driver {

    private static Activity visitBuilding = new Activity();
    private static Activity attendLecture = new Activity();
    private static Activity pickGift = new Activity();
    private static Activity selectCafeteria = new Activity();

    public static void main(String[] args) {

        decideActivities();
        CampusTourPlanner campusTourPlanner = new CampusTour(visitBuilding, attendLecture, pickGift, selectCafeteria);
        CampusTourWorkshopI campusTour = new CampusTourWorkshop();
        campusTour.construct(campusTourPlanner);
        System.out.println(Effects.sb);
    }

    private static void decideActivities() {

        // For activity 1
        visitBuilding.setOrientationItinerary(OrientationItinerary.VISIT_SOM);
        visitBuilding.setTransportationMode(TransportationMode.Bus);

        // For activity 2
        attendLecture.setOrientationItinerary(OrientationItinerary.ATTEND_LECTURE_CS350);
        attendLecture.setTransportationMode(TransportationMode.OnFoot);

        // For activity 3
        pickGift.setOrientationItinerary(OrientationItinerary.PICKUP_GIFT_EC);
        pickGift.setTransportationMode(TransportationMode.Bus);

        // For activity 4
        selectCafeteria.setOrientationItinerary(OrientationItinerary.SELECT_CAFETERIA_CIW);
        selectCafeteria.setTransportationMode(TransportationMode.OnFoot);
    }

    /**
     * @param arguments The arguments passed to the program
     * @return boolean representing whether the sufficient arguments are passed to the program or not
     */
    private static boolean validate(String[] arguments) {

        try {
            if (arguments.length < 4 || arguments[0].trim().length() <= 0 || arguments[1].trim().length() <= 0
                    || arguments[0].equals("${arg0}") || arguments[1].equals("${arg1}")) {
                System.err.println("Invalid parameters entered");
                System.err.println("Please enter <inputFile> <OutputFile>");
                System.err.println("Exiting program");
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException iae) {
            return false;
        } finally {
        }
        return true;
    }

    @Override
    public String toString() {
        return "Driver{}";
    }
}