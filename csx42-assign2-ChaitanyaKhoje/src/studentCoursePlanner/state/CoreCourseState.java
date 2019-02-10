package studentCoursePlanner.state;

public class CoreCourseState implements CoursePlannerStateI{

    private CourseManager courseManager;

    public CoreCourseState(CourseManager courseManagerIn) {
        courseManager = courseManagerIn;
    }

    @Override
    public void enroll(Action action) {
        if (action.equals(Action.ELECTIVE_ASSIGNED)) {
            courseManager.setState(courseManager.getElectiveCourseState());
        }
    }

    @Override
    public void graduate(StudentManager studentManager, Action action) {
        if ((studentManager.isCategory1Satisfied()
                && studentManager.isCategory2Satisfied()
                && studentManager.isCategory3Satisfied()
                && studentManager.isCategory4Satisfied()
                && studentManager.isCategory5Satisfied())
                && action.equals(Action.PREREQ_SATISFIED)) {
            System.out.println("Student has graduated.");
            courseManager.setState(courseManager.getGraduatedStudentState());
        } else {
            courseManager.setAction(Action.PREREQ_VIOLATED);
        }
    }

    @Override
    public String toString() {
        return "CoreCourseState{" +
                "courseManager=" + courseManager +
                '}';
    }
}
