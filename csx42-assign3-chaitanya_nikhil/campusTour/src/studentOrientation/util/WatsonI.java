package studentOrientation.util;

public interface WatsonI {

    public boolean calculateEffects(Units units,TransportationMode transport);
    public void visitWatson (OrientationItinerary location);
    public int getGiftCost();
    public float getCarbonFootprint();
    public float getDuration();
    public float getEfforts();
}
