package studentOrientation.util;

public class CarbonFootprint implements CarbonFootprintI {

    private double emissionFactor = 0.24234;
    @Override
    public void getEmittedCO2(Activity activity) {

        if (activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_SOM)) {
            if (activity.getTransportationMode().equals(TransportationMode.Bus)) {
                Effects.sb.append("Carbon Footprint: " + getEmissionFactor() *
                        CampusTourWorkshop.getVisitSOM().getDistanceToSOM() * CampusTourWorkshop.getVisitSOM().getDuration());
                Effects.sb.append(System.getProperty("line.separator"));
            } else {
                Effects.sb.append("Carbon Footprint: " + getEmissionFactor() * CampusTourWorkshop.getVisitSOM().getDistanceToSOM());
                Effects.sb.append(System.getProperty("line.separator"));
            }
        } else if (activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_WATSON)) {
            if (activity.getTransportationMode().equals(TransportationMode.Bus)) {
                Effects.sb.append("Carbon Footprint: " + getEmissionFactor()
                        * CampusTourWorkshop.getVisitWatson().getDistanceToWatson() * CampusTourWorkshop.getVisitWatson().getDuration());
                Effects.sb.append(System.getProperty("line.separator"));
            } else {
                Effects.sb.append("Carbon Footprint: " + getEmissionFactor() * CampusTourWorkshop.getVisitWatson().getDistanceToWatson());
                Effects.sb.append(System.getProperty("line.separator"));
            }
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_CIW))) {
            Effects.sb.append("Carbon Footprint: " + getEmissionFactor()
                    * CampusTourWorkshop.getCafeteria().getDistanceToCIW() * CampusTourWorkshop.getCafeteria().getDurationCafeteria());
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_MW))) {
            Effects.sb.append("Carbon Footprint: " + getEmissionFactor()
                    * CampusTourWorkshop.getCafeteria().getDistanceToMW() * CampusTourWorkshop.getCafeteria().getDurationCafeteria());
            Effects.sb.append(System.getProperty("line.separator"));
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS350))) {
            Effects.sb.append("Carbon Footprint: " + getEmissionFactor() * CampusTourWorkshop.getLecture().getDurationLecture());
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS240))) {
            Effects.sb.append("Carbon Footprint: " + getEmissionFactor() * CampusTourWorkshop.getLecture().getDurationLecture());
            Effects.sb.append(System.getProperty("line.separator"));
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_UU))) {
            Effects.sb.append("Carbon Footprint: " + getEmissionFactor() * CampusTourWorkshop.getGift().getDistanceToUU());
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_EC))) {
            Effects.sb.append("Carbon Footprint: " + getEmissionFactor() * CampusTourWorkshop.getGift().getDistanceToEC());
            Effects.sb.append(System.getProperty("line.separator"));
        }
    }

    @Override
    public void setEmittedCO2() {

    }

    public double getEmissionFactor() {
        return emissionFactor;
    }

    public void setEmissionFactor(double emissionFactor) {
        this.emissionFactor = emissionFactor;
    }
}
