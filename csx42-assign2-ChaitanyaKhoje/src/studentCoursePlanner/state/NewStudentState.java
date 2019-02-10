package studentCoursePlanner.state;

public class NewStudentState implements CoursePlannerStateI {

    private CourseManager courseManager;

    public NewStudentState(CourseManager courseManagerIn) {
        courseManager = courseManagerIn;
    }

    @Override
    public void enroll(Action action) {
        if (action.equals(Action.CORE_ASSIGNED) || action.equals(Action.WAITING_COURSE_ASSIGNED)) {
            courseManager.setState(courseManager.getCoreCourseState());
            //System.out.println("Changed to CURRENT STUDENT STATE");
        } else if (action.equals(Action.ELECTIVE_ASSIGNED)) {
            courseManager.setState(courseManager.getElectiveCourseState());
        } else if (action.equals(Action.NOCOURSES) || action.equals(Action.QUEUED)) {
            courseManager.setState(courseManager.getNewStudentState());
            //System.out.println("No course assigned. New Student State preserved.");
        }
    }

    @Override
    public void graduate(StudentManager studentManager, Action action) {
        //System.out.println("The student needs to take courses and fulfill the prerequisites.");
        if (action.equals(Action.CORE_ASSIGNED) || action.equals(Action.WAITING_COURSE_ASSIGNED)) {
            courseManager.setState(courseManager.getCoreCourseState());
        } else if (action.equals(Action.ELECTIVE_ASSIGNED)) {
            courseManager.setState(courseManager.getElectiveCourseState());
        }
    }

    @Override
    public String toString() {
        return "NewStudentState{" +
                "courseManager=" + courseManager +
                '}';
    }
}
