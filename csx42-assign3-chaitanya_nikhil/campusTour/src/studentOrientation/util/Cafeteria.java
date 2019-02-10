package studentOrientation.util;

public class Cafeteria implements CafeteriaI {

    OrientationItinerary location;
    private double onlineOption = 0.05;     // Surcharge on online option
    private double distanceToCIW = 3;
    private double distanceToMW = 2;
    private double durationCafeteria = 25;
    private double foodCost = 10;

    @Override
    public boolean calculateEffects(Units units) {
        return false;
    }

    @Override
    public void setCafeteriaLocation(OrientationItinerary locationIn) {

        location=locationIn;
    }

    public OrientationItinerary getLocation() {
        return location;
    }

    public void setLocation(OrientationItinerary location) {
        this.location = location;
    }

    public double getOnlineOption() {
        return onlineOption;
    }

    public void setOnlineOption(double onlineOption) {
        this.onlineOption = onlineOption;
    }

    public double getDistanceToCIW() {
        return distanceToCIW;
    }

    public void setDistanceToCIW(double distanceToCIW) {
        this.distanceToCIW = distanceToCIW;
    }

    public double getDistanceToMW() {
        return distanceToMW;
    }

    public void setDistanceToMW(double distanceToMW) {
        this.distanceToMW = distanceToMW;
    }

    public double getDurationCafeteria() {
        return durationCafeteria;
    }

    public void setDurationCafeteria(double durationCafeteria) {
        this.durationCafeteria = durationCafeteria;
    }

    public double getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(double foodCost) {
        this.foodCost = foodCost;
    }

    @Override
    public String toString() {
        return "Cafeteria{" +
                "location=" + location +
                ", onlineOption=" + onlineOption +
                ", distanceToCIW=" + distanceToCIW +
                ", distanceToMW=" + distanceToMW +
                '}';
    }
}
