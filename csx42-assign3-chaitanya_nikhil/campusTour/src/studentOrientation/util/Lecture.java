package studentOrientation.util;

public class Lecture implements LectureI {

    private double durationLecture = 60;    // In minutes
    private double inClassOptionSurcharge = 0.1;     // Surcharge on in class option
    private double lectureFee = 100;

    public Lecture() {}

    @Override
    public float getDuration() {
        return 0;
    }

    public double getDurationLecture() {
        return durationLecture;
    }

    public void setDurationLecture(double durationLecture) {
        this.durationLecture = durationLecture;
    }

    public double getInClassOptionSurcharge() {
        return inClassOptionSurcharge;
    }

    public void setInClassOptionSurcharge(double inClassOptionSurcharge) {
        this.inClassOptionSurcharge = inClassOptionSurcharge;
    }


    public double getLectureFee() {
        return lectureFee;
    }

    public void setLectureFee(double lectureFee) {
        this.lectureFee = lectureFee;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "durationLecture=" + durationLecture +
                ", inClassOptionSurcharge=" + inClassOptionSurcharge +
                '}';
    }
}
