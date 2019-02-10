package studentCoursePlanner.state;

public class CourseManager {

    private CoursePlannerStateI newStudentState;                // This is the first base state.
    private CoursePlannerStateI coreCourseState;
    private CoursePlannerStateI electiveCourseState;
    private CoursePlannerStateI graduatedStudentState;          // This is the final state
    private CoursePlannerStateI cannotGraduateState;
    private CoursePlannerStateI state;
    private Action action;

    public CourseManager() {

        newStudentState = new NewStudentState(this);
        coreCourseState = new CoreCourseState(this);
        electiveCourseState = new ElectiveCourseState(this);
        graduatedStudentState = new GraduatedStudentState(this);
        cannotGraduateState = new CannotGraduateState(this);
        state = newStudentState;
    }

    /**
     * This method;
     * Takes in the filepath in string.
     * Reads file and builds the courses arraylist.
     * The student is enrolled in courses.
     * Waiting courses are assigned.
     * A boolean variable; graduated, is returned to check if the prereqs are satisfied.
     * @param arg
     * @return graduated
     */
    public boolean processStudentGraduation(String arg) {

        StudentManager studentManager = new StudentManager();
        studentManager.initializeStudentManager(arg);
        enrollStudent(studentManager);
        if (!studentManager.getWaitingCourses().isEmpty()) action = Action.QUEUED;
        graduateStudent(studentManager, action);
        boolean graduated = false;
        if (!action.equals(Action.PREREQ_VIOLATED)) graduated = true;
        return graduated;
    }

    /**
     * This is called within processStudentGraduation.
     * Iterates over the ArrayList of courses that was built while reading the file.
     * Processes the courses; this is the main functionality where the courses are either assigned or put into the waiting list.
     * After processing, the enum Action is returned and the particular action tells the following state on what to do.
     * @param studentManager
     */
    public void enrollStudent(StudentManager studentManager) {

        //for (String course : StudentManager.getCourses())
        for (int i = 0; i < StudentManager.getCourses().size(); i++) {
            action = studentManager.processCourses(StudentManager.getCourses().get(i));
            if (action.equals(Action.WAITING_COURSE_ASSIGNED)) i = i - 1;
            if (!action.equals(Action.NOACTION)) state.enroll(action);
        }
    }

    public void graduateStudent(StudentManager studentManager, Action action) {
        if (action.equals(Action.QUEUED)) {
            studentManager.assignWaitingCourses();
        }

        int requiredCourses = StudentManager.getCoursesTaken().size();
        if (studentManager.getWaitingCourses().isEmpty() && requiredCourses >= 10) {
            action = Action.PREREQ_SATISFIED;
        } else {
            action = Action.PREREQ_VIOLATED;
        }
        state.graduate(studentManager, action);
    }

    public CoursePlannerStateI getNewStudentState() {
        return newStudentState;
    }

    public void setNewStudentState(CoursePlannerStateI newStudentStateIn) {
        newStudentState = newStudentStateIn;
    }

    public CoursePlannerStateI getCoreCourseState() {
        return coreCourseState;
    }

    public void setCoreCourseState(CoursePlannerStateI coreCourseStateIn) {
        coreCourseState = coreCourseStateIn;
    }

    public CoursePlannerStateI getElectiveCourseState() {
        return electiveCourseState;
    }

    public void setElectiveCourseState(CoursePlannerStateI electiveCourseStateIn) {
        electiveCourseState = electiveCourseStateIn;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action actionIn) {
        action = actionIn;
    }

    public CoursePlannerStateI getGraduatedStudentState() {
        return graduatedStudentState;
    }

    public void setGraduatedStudentState(CoursePlannerStateI graduatedStudentStateIn) {
        graduatedStudentState = graduatedStudentStateIn;
    }

    public CoursePlannerStateI getCannotGraduateState() {
        return cannotGraduateState;
    }

    public void setCannotGraduateState(CoursePlannerStateI cannotGraduateStateIn) {
        cannotGraduateState = cannotGraduateStateIn;
    }

    public CoursePlannerStateI getState() {
        return state;
    }

    public void setState(CoursePlannerStateI stateIn) {
        state = stateIn;
    }

    @Override
    public String toString() {
        return "CourseManager{" +
                "newStudentState=" + newStudentState +
                ", coreCourseState=" + coreCourseState +
                ", electiveCourseState=" + electiveCourseState +
                ", graduatedStudentState=" + graduatedStudentState +
                ", cannotGraduateState=" + cannotGraduateState +
                ", state=" + state +
                ", action=" + action +
                '}';
    }
}
