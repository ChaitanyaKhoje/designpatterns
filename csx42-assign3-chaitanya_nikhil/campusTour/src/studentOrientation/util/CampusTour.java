package studentOrientation.util;

public class CampusTour extends CampusTourPlanner {

    private Activity building;
    private Activity gift;
    private Activity cafeteria;
    private Activity lecture;

    public CampusTour (Activity buildingIn, Activity lectureIn,
                       Activity giftIn, Activity cafeteriaIn) {

        building = buildingIn;
        gift = giftIn;
        cafeteria = cafeteriaIn;
        lecture = lectureIn;
    }

    @Override
    public void visitBuilding() {
        building.setActivityName(ParentActivityName.Building);
        CampusTourWorkshop.getEffects().generateEffects(building);
    }

    @Override
    public void pickGift() {
        gift.setActivityName(ParentActivityName.Gift);
        CampusTourWorkshop.getEffects().generateEffects(gift);
    }

    @Override
    public void selectCafeteria() {
        cafeteria.setActivityName(ParentActivityName.Cafeteria);
        CampusTourWorkshop.getEffects().generateEffects(cafeteria);
    }

    @Override
    public void attendLecture() {
        lecture.setActivityName(ParentActivityName.Lecture);
        CampusTourWorkshop.getEffects().generateEffects(lecture);
    }

    public Activity getBuilding() {
        return building;
    }

    public void setBuilding(Activity building) {
        this.building = building;
    }

    public Activity getGift() {
        return gift;
    }

    public void setGift(Activity gift) {
        this.gift = gift;
    }

    public Activity getCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(Activity cafeteria) {
        this.cafeteria = cafeteria;
    }

    public Activity getLecture() {
        return lecture;
    }

    public void setLecture(Activity lecture) {
        this.lecture = lecture;
    }
}