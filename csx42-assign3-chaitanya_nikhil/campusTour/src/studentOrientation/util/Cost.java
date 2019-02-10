package studentOrientation.util;

public class Cost implements CostI {

    public Cost() {}

    @Override
    public void getCost(Activity activity) {

        double cost = 0;
        if (activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_SOM)) {
            if (activity.getTransportationMode().equals(TransportationMode.Bus)) {
                cost = CampusTourWorkshop.getEffects().getBusCost() * CampusTourWorkshop.getVisitSOM().getDistanceToSOM();
                Effects.sb.append("Cost: " + cost + " (Bus cost * distance)");
                Effects.sb.append(System.getProperty("line.separator"));
            } else {
                cost = CampusTourWorkshop.getEffects().getOnFootCost() * CampusTourWorkshop.getVisitSOM().getDistanceToSOM();
                Effects.sb.append("Cost: " + cost + " (On-foot cost * distance)");
                Effects.sb.append(System.getProperty("line.separator"));
            }
        } else if (activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_WATSON)) {
            if (activity.getTransportationMode().equals(TransportationMode.Bus)) {
                cost = CampusTourWorkshop.getEffects().getBusCost() * CampusTourWorkshop.getVisitWatson().getDistanceToWatson();
                Effects.sb.append("Cost: " + cost + " (Bus cost * distance)");
                Effects.sb.append(System.getProperty("line.separator"));
            } else {
                cost = CampusTourWorkshop.getEffects().getOnFootCost() * CampusTourWorkshop.getVisitWatson().getDistanceToWatson();
                Effects.sb.append("Cost: " + cost + " (On-foot cost * distance)");
                Effects.sb.append(System.getProperty("line.separator"));
            }
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS350))) {
            Effects.sb.append("Cost: " + CampusTourWorkshop.getLecture().getLectureFee() + "(Lecture fee for CS350)");
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS240))) {
            Effects.sb.append("Cost: " + (CampusTourWorkshop.getLecture().getLectureFee()
                    + (CampusTourWorkshop.getLecture().getLectureFee() * CampusTourWorkshop.getLecture().getInClassOptionSurcharge()))
                    + "(Lecture fee for CS240 + 10% cost surcharge)");
            Effects.sb.append(System.getProperty("line.separator"));
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_EC))) {
            Effects.sb.append("Cost: " + (CampusTourWorkshop.getGift().getDistanceToEC() + CampusTourWorkshop.getGift().getTravellingCost()) + "(distance to Event Center + travelling cost($2))");
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_UU))) {
            Effects.sb.append("Cost: " + (CampusTourWorkshop.getGift().getDistanceToUU() + CampusTourWorkshop.getGift().getTravellingCost()) + "(distance to University Union + travelling cost($2))");
            Effects.sb.append(System.getProperty("line.separator"));
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_MW))) {
            Effects.sb.append("Cost: " + (CampusTourWorkshop.getCafeteria().getFoodCost()
                    + (CampusTourWorkshop.getCafeteria().getFoodCost() * CampusTourWorkshop.getCafeteria().getOnlineOption()))
                    + "(Food cost($10) + 5% surcharge)");
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_CIW))) {
            Effects.sb.append("Cost: " + (CampusTourWorkshop.getCafeteria().getFoodCost() + (CampusTourWorkshop.getCafeteria().getFoodCost())) + "(Food cost($10))");
            Effects.sb.append(System.getProperty("line.separator"));
        }
    }

    @Override
    public void setCost() {

    }

    @Override
    public void setTotalExpenditure() {

    }
}
