package studentCoursePlanner.state;

public class CannotGraduateState implements CoursePlannerStateI {

    private CourseManager courseManager;

    public CannotGraduateState(CourseManager courseManagerIn) {
        courseManager = courseManagerIn;
    }

    @Override
    public void enroll(Action action) {
        //System.out.println("The student cannot be enrolled in the course as prerequisites are not satisfied.");
        courseManager.setState(courseManager.getCannotGraduateState());
    }

    @Override
    public void graduate(StudentManager studentManager, Action action) {
        //System.out.println("As the courses cannot be assigned due to prerequisites not being satisfied, the student cannot graduate.");
        courseManager.setState(courseManager.getCannotGraduateState());
    }

    @Override
    public String toString() {
        return "CannotGraduateState{" +
                "courseManager=" + courseManager +
                '}';
    }
}
