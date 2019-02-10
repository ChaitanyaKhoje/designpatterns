package studentOrientation.util;

public class CampusTourWorkshop implements CampusTourWorkshopI {

    private static Effects effects = new Effects();
    private static VisitWatson visitWatson = new VisitWatson();
    private static VisitSOM visitSOM = new VisitSOM();
    private static Gift gift = new Gift();
    private static Lecture lecture = new Lecture();
    private static Cafeteria cafeteria = new Cafeteria();
    private static Duration duration = new Duration();
    private static Cost cost = new Cost();
    private static CarbonFootprint carbonFootprint = new CarbonFootprint();
    private static Efforts efforts = new Efforts();

    public void construct(CampusTourPlanner campusTourPlanner) {

        campusTourPlanner.visitBuilding();
        campusTourPlanner.pickGift();
        campusTourPlanner.selectCafeteria();
        campusTourPlanner.attendLecture();
    }

    public static Effects getEffects() {
        return effects;
    }

    public static void setEffects(Effects effects) {
        CampusTourWorkshop.effects = effects;
    }

    public static VisitWatson getVisitWatson() {
        return visitWatson;
    }

    public static void setVisitWatson(VisitWatson visitWatson) {
        CampusTourWorkshop.visitWatson = visitWatson;
    }

    public static VisitSOM getVisitSOM() {
        return visitSOM;
    }

    public static void setVisitSOM(VisitSOM visitSOM) {
        CampusTourWorkshop.visitSOM = visitSOM;
    }

    public static Gift getGift() {
        return gift;
    }

    public static void setGift(Gift gift) {
        CampusTourWorkshop.gift = gift;
    }

    public static Lecture getLecture() {
        return lecture;
    }

    public static void setLecture(Lecture lecture) {
        CampusTourWorkshop.lecture = lecture;
    }

    public static Cafeteria getCafeteria() {
        return cafeteria;
    }

    public static void setCafeteria(Cafeteria cafeteria) {
        CampusTourWorkshop.cafeteria = cafeteria;
    }

    public static Duration getDuration() {
        return duration;
    }

    public static void setDuration(Duration duration) {
        CampusTourWorkshop.duration = duration;
    }

    public static Cost getCost() {
        return cost;
    }

    public static void setCost(Cost cost) {
        CampusTourWorkshop.cost = cost;
    }

    public static CarbonFootprint getCarbonFootprint() {
        return carbonFootprint;
    }

    public static void setCarbonFootprint(CarbonFootprint carbonFootprint) {
        CampusTourWorkshop.carbonFootprint = carbonFootprint;
    }

    public static Efforts getEfforts() {
        return efforts;
    }

    public static void setEfforts(Efforts efforts) {
        CampusTourWorkshop.efforts = efforts;
    }
}
