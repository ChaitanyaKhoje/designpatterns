package studentOrientation.util;

public class VisitWatson implements WatsonI {

    private double distanceToWatson = 2;    // In km
    private double durationWatson = 20; // In minutes

    public VisitWatson() {}

    @Override
    public boolean calculateEffects(Units units, TransportationMode transport) {
        return false;
    }

    @Override
    public void visitWatson(OrientationItinerary location) {

    }

    @Override
    public int getGiftCost() {
        return 0;
    }

    @Override
    public float getCarbonFootprint() {
        return 0;
    }

    @Override
    public float getDuration() {
        return 0;
    }

    @Override
    public float getEfforts() {
        return 0;
    }

    public double getDistanceToWatson() {
        return distanceToWatson;
    }

    public void setDistanceToWatson(double distanceToWatson) {
        this.distanceToWatson = distanceToWatson;
    }

    @Override
    public String toString() {
        return "VisitWatson{" +
                "distanceToWatson=" + distanceToWatson +
                ", durationWatson=" + durationWatson +
                '}';
    }
}
