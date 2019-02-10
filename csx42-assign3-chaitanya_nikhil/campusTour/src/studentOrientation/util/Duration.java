package studentOrientation.util;

public class Duration implements DurationI {

    @Override
    public void getDuration(Activity activity) {

        if (activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_SOM)) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getVisitSOM().getDuration());
            Effects.sb.append(System.getProperty("line.separator"));
        } else if (activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_WATSON)) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getVisitWatson().getDuration());
            Effects.sb.append(System.getProperty("line.separator"));
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_EC))) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getGift().getDurationToEC());
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_UU))) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getGift().getDurationToUU());
            Effects.sb.append(System.getProperty("line.separator"));
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_CIW))) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getCafeteria().getDurationCafeteria());
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_MW))) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getCafeteria().getDurationCafeteria());
            Effects.sb.append(System.getProperty("line.separator"));
        }

        if ((activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS240))) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getLecture().getDurationLecture());
            Effects.sb.append(System.getProperty("line.separator"));
        } else if ((activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS350))) {
            Effects.sb.append("Duration: " + CampusTourWorkshop.getLecture().getDurationLecture());
            Effects.sb.append(System.getProperty("line.separator"));
        }
    }

    @Override
    public void setDuration() {

    }

    @Override
    public void calculateDuration() {

    }
}
