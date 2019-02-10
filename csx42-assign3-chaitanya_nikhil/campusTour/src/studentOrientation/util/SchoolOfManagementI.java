package studentOrientation.util;

public interface SchoolOfManagementI {

    public boolean calculateEffects(Units units,TransportationMode transport);
    public void visitSom (OrientationItinerary location);
    public int getGiftCost();
    public float getCarbonFootprint();
    public float getDuration();
    public float getEfforts();
}
