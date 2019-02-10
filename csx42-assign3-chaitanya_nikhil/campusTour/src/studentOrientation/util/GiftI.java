package studentOrientation.util;

public interface GiftI {

    public boolean calculateEffects(Units units,TransportationMode transport);
    public void setGiftLocation (OrientationItinerary location);
    public float getDuration();

}
