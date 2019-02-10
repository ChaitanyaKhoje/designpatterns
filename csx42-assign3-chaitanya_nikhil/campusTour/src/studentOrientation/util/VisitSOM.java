package studentOrientation.util;

public class VisitSOM implements SchoolOfManagementI {

    private double distanceToSOM = 4;       // In km
    private double durationSOM = 30; // In minutes

    public VisitSOM() {}

    @Override
    public boolean calculateEffects(Units units, TransportationMode transport) {
        return false;
    }

    @Override
    public void visitSom(OrientationItinerary location) {

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

    public double getDistanceToSOM() {
        return distanceToSOM;
    }

    public void setDistanceToSOM(double distanceToSOM) {
        this.distanceToSOM = distanceToSOM;
    }

    public double getDurationSOM() {
        return durationSOM;
    }

    public void setDurationSOM(double durationSOM) {
        this.durationSOM = durationSOM;
    }

    @Override
    public String toString() {
        return "VisitSOM{" +
                "distanceToSOM=" + distanceToSOM +
                ", durationSOM=" + durationSOM +
                '}';
    }
}
