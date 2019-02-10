package studentCoursePlanner.state;

public interface CoursePlannerStateI {

    void enroll(Action action);
    void graduate(StudentManager studentManager, Action action);
}
