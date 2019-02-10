package studentOrientation.util;

import java.util.ArrayList;

public class Activity {

    private OrientationItinerary orientationItinerary;
    private TransportationMode transportationMode;
    private ParentActivityName activityName;

    public static ArrayList<Activity> activities = new ArrayList<Activity>();

    public Activity() {}

    private ArrayList<Activity> getActivities(Activity activity) {

        activities.add(activity);
        return activities;
    }

    public OrientationItinerary getOrientationItinerary() {
        return orientationItinerary;
    }

    public void setOrientationItinerary(OrientationItinerary orientationItinerary) {

        this.orientationItinerary = orientationItinerary;
    }

    public TransportationMode getTransportationMode() {
        return transportationMode;
    }

    public void setTransportationMode(TransportationMode transportationMode) {

        this.transportationMode = transportationMode;
    }

    public ParentActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ParentActivityName activityName) {
        this.activityName = activityName;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "orientationItinerary=" + orientationItinerary +
                ", transportationMode=" + transportationMode +
                '}';
    }
}
