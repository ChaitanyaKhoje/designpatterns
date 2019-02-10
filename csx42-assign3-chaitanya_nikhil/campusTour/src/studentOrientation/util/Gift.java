package studentOrientation.util;

public class Gift implements GiftI {

    private double distanceToEC = 2;
    private double distanceToUU = 3;
    private double travellingCost = 2;
    private double durationToEC = 30;
    private double durationToUU = 25;

    @Override
    public boolean calculateEffects(Units units, TransportationMode transport) {
        return false;
    }

    @Override
    public void setGiftLocation(OrientationItinerary location) {

    }

    @Override
    public float getDuration() {
        return 0;
    }

    public double getDistanceToEC() {
        return distanceToEC;
    }

    public void setDistanceToEC(double distanceToEC) {
        this.distanceToEC = distanceToEC;
    }

    public double getDistanceToUU() {
        return distanceToUU;
    }

    public void setDistanceToUU(double distanceToUU) {
        this.distanceToUU = distanceToUU;
    }

    public double getTravellingCost() {
        return travellingCost;
    }

    public void setTravellingCost(double travellingCost) {
        this.travellingCost = travellingCost;
    }

    public double getDurationToEC() {
        return durationToEC;
    }

    public void setDurationToEC(double durationToEC) {
        this.durationToEC = durationToEC;
    }

    public double getDurationToUU() {
        return durationToUU;
    }

    public void setDurationToUU(double durationToUU) {
        this.durationToUU = durationToUU;
    }
}
