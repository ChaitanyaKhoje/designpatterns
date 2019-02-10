package studentCoursePlanner.state;

public class GraduatedStudentState implements CoursePlannerStateI {

    private CourseManager courseManager;

    public GraduatedStudentState(CourseManager courseManagerIn) {
        courseManager = courseManagerIn;
    }

    @Override
    public void enroll(Action action) {
        courseManager.setState(courseManager.getGraduatedStudentState());
    }

    @Override
    public void graduate(StudentManager studentManager, Action action) {
        courseManager.setState(courseManager.getGraduatedStudentState());
    }

    @Override
    public String toString() {
        return "GraduatedStudentState{" +
                "courseManager=" + courseManager +
                '}';
    }
}
