package studentOrientation.util;

public class Effects{

    private double busCost = 1.00;          // In $
    private double onFootCost = 0.50;       // In $

//    private static Cost cost = new Cost();
//    private static Duration duration = new Duration();
//    private static Efforts efforts = new Efforts();
//    private static CarbonFootprint carbonFootprint = new CarbonFootprint();

    public static String result = "";
    public static StringBuilder sb = new StringBuilder();

    public Effects() {}

    public void generateEffects(Activity activity) {

        switch (activity.getActivityName()) {
            case Building:
                sb.append("Visit building: ");
                sb.append("\n");
                break;
            case Gift:
                sb.append("Picking a gift: ");
                sb.append("\n");

                break;
            case Lecture:
                sb.append("Attending a lecture: ");
                sb.append("\n");
                break;
            case Cafeteria:
                sb.append("Selecting a cafeteria: ");
                sb.append("\n");
                break;
        }
        CampusTourWorkshop.getDuration().getDuration(activity);
        CampusTourWorkshop.getCost().getCost(activity);
        CampusTourWorkshop.getEfforts().getCalories(activity);
        CampusTourWorkshop.getCarbonFootprint().getEmittedCO2(activity);
        sb.append("\n");
    }


    public double getBusCost() {
        return busCost;
    }

    public void setBusCost(double busCost) {
        this.busCost = busCost;
    }

    public double getOnFootCost() {
        return onFootCost;
    }

    public void setOnFootCost(double onFootCost) {
        this.onFootCost = onFootCost;
    }
}
