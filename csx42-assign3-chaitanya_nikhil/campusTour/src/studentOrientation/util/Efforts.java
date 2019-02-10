package studentOrientation.util;

public class Efforts implements EffortI {

    private double caloriesVisitB = 1000;
    private double caloriesLecture = 200;
    private double caloriesGift = 800;
    private double caloriesCafeteria = 1200;

    @Override
    public void setCalories() {

    }

    @Override
    public void getCalories(Activity activity) {
        if ((activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_SOM))
                || (activity.getOrientationItinerary().equals(OrientationItinerary.VISIT_WATSON))) {
            Effects.sb.append("Efforts: " + getCaloriesVisitB());
            Effects.sb.append(System.getProperty("line.separator"));
        }
        if ((activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_UU))
                || activity.getOrientationItinerary().equals(OrientationItinerary.PICKUP_GIFT_EC)) {
            Effects.sb.append("Efforts: " + getCaloriesGift());
            Effects.sb.append(System.getProperty("line.separator"));
        }
        if ((activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS240))
                || activity.getOrientationItinerary().equals(OrientationItinerary.ATTEND_LECTURE_CS350)) {
            Effects.sb.append("Efforts: " + getCaloriesLecture());
            Effects.sb.append(System.getProperty("line.separator"));
        }
        if((activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_MW))
                || activity.getOrientationItinerary().equals(OrientationItinerary.SELECT_CAFETERIA_CIW)) {
            Effects.sb.append("Efforts: " + getCaloriesCafeteria());
            Effects.sb.append(System.getProperty("line.separator"));
        }
    }

    @Override
    public void calculateEfforts() {

    }

    public double getCaloriesVisitB() {
        return caloriesVisitB;
    }

    public void setCaloriesVisitB(double caloriesVisitB) {
        this.caloriesVisitB = caloriesVisitB;
    }

    public double getCaloriesLecture() {
        return caloriesLecture;
    }

    public void setCaloriesLecture(double caloriesLecture) {
        this.caloriesLecture = caloriesLecture;
    }

    public double getCaloriesGift() {
        return caloriesGift;
    }

    public void setCaloriesGift(double caloriesGift) {
        this.caloriesGift = caloriesGift;
    }

    public double getCaloriesCafeteria() {
        return caloriesCafeteria;
    }

    public void setCaloriesCafeteria(double caloriesCafeteria) {
        this.caloriesCafeteria = caloriesCafeteria;
    }
}
