package studentCoursePlanner.state;

import studentCoursePlanner.util.FileProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class deals with;
 * Reading the input file.
 * Storing the BNumber to a variable (bNumber) and all the courses for that BNo in an ArrayList.
 */
public class StudentManager {

    private static int bNumber;
    private static ArrayList<String> courses;                       // Input file courses
    private static List<String> coursesTaken;    // Assigned courses
    private List<String> waitingCourses;  // Waiting list

    private int elementIndex;
    private int firstIndex;
    private String waitingCourse;
    private boolean lowerOrderCourseAssigned = false;
    private int category;
    private boolean courseQueued;

    private List<String> category1;
    private List<String> category2;
    private List<String> category3;
    private List<String> category4;
    private List<String> category5;

    // Flags to denote that atleast 2 courses are taken by the student from a particular category.
    private boolean category1Satisfied;
    private boolean category2Satisfied;
    private boolean category3Satisfied;
    private boolean category4Satisfied;
    private boolean category5Satisfied;

    private static int semesterCount = 0;

    public StudentManager() {
        coursesTaken = new ArrayList<String>();
        waitingCourses = new ArrayList<String>();
    }

    /**
     * This method takes in the filepath as parameter.
     * The file processor's constructor is called in which the filepath is passed and the scanner is initialized.
     * An ArrayList of courses is built from which we will process all input courses one by one.
     * Temporary ArrayLists of all categories are made using segregateCourses (this is later on used for comparing purposes).
     * @param arg
     */
    public void initializeStudentManager(String arg) {

        FileProcessor fileProcessor = new FileProcessor(arg);
        String fileLine = fileProcessor.getNextLine();
        addCoursesInList(fileLine);
        segregateCourses();
    }

    public Action processCourses(String course) {

        Action action = Action.NOACTION;
        if (!course.equals("")) {
            if (!(category1Satisfied
                    && category2Satisfied
                    && category3Satisfied
                    && category4Satisfied
                    && category5Satisfied)) {
                if (course.matches("[A-D]")) {
                    category = 1;
                    elementIndex = category1.indexOf(course);
                    firstIndex = category1.indexOf(category1.get(0));
                    if (checkWaitingList(1, course)) {
                        action = Action.WAITING_COURSE_ASSIGNED;
                        courseQueued = false;
                    } else if (action.equals(Action.NOACTION)) {
                        lowerOrderCourseAssigned = assignCourses(elementIndex, firstIndex, category, course, lowerOrderCourseAssigned);
                    }
                } else if (course.matches("[E-H]")) {
                    category = 2;
                    elementIndex = category2.indexOf(course);
                    firstIndex = category2.indexOf(category2.get(0));
                    //System.out.println("The course is found in category1");
                    if (checkWaitingList(2, course)) {
                        action = Action.WAITING_COURSE_ASSIGNED;
                        courseQueued = false;
                    } else if (action.equals(Action.NOACTION)) {
                        lowerOrderCourseAssigned = assignCourses(elementIndex, firstIndex, category, course, lowerOrderCourseAssigned);
                    }
                } else if (course.matches("[I-L]")) {
                    category = 3;
                    elementIndex = category3.indexOf(course);
                    firstIndex = category3.indexOf(category3.get(0));
                    //System.out.println("The course is found in category1");
                    if (checkWaitingList(3, course)) {
                        action = Action.WAITING_COURSE_ASSIGNED;
                        courseQueued = false;
                    } else if (action.equals(Action.NOACTION)) {
                        lowerOrderCourseAssigned = assignCourses(elementIndex, firstIndex, category, course, lowerOrderCourseAssigned);
                    }
                } else if (course.matches("[M-P]")) {
                    category = 4;
                    elementIndex = category4.indexOf(course);
                    firstIndex = category4.indexOf(category4.get(0));
                    //System.out.println("The course is found in category1");
                    if (checkWaitingList(4, course)) {
                        action = Action.WAITING_COURSE_ASSIGNED;
                        courseQueued = false;
                    } else if (action.equals(Action.NOACTION)) {
                        lowerOrderCourseAssigned = assignCourses(elementIndex, firstIndex, category, course, lowerOrderCourseAssigned);
                    }
                } else if (course.matches("[Q-Z]")) {
                    category = 5;
                    coursesTaken.add(course);
                    satisfyPreReq(category);
                }
                // Increment the number of semesters.
                semesterCount = 0;
                double temp = coursesTaken.size();
                double ceilValue = (temp/3);
                semesterCount = (int) Math.ceil(ceilValue);
//                StudentManager.setSemesterCount((int) Math.ceil(StudentManager.getCourses().size()/3));
//                if (!coursesTaken.isEmpty() && ((coursesTaken.size() % 3) == 0)) {
//                    semesterCount++;
//                }
            }
        } else {
            return action = Action.NOCOURSES;
        }
        if (!courseQueued && category != 5 && !action.equals(Action.WAITING_COURSE_ASSIGNED)) {
            return action = Action.CORE_ASSIGNED;
        } else if (!courseQueued && category == 5) {
            return action = Action.ELECTIVE_ASSIGNED;
        } else if (!action.equals(Action.WAITING_COURSE_ASSIGNED)) {
            return action = Action.QUEUED;
        }
        return action;
    }

    /**
     * The core method for assigning courses.
     * The courses are assigned by adding to a list; coursesTaken
     * A course waiting-list is also maintained as per the prerequisites' guidelines.
     * @param elementIndex
     * @param firstIndex
     * @param category
     * @param course
     * @param lowerOrderCourseAssigned
     * @return lowerOrderCourseAssigned
     */
    public boolean assignCourses(int elementIndex, int firstIndex, int category, String course, boolean lowerOrderCourseAssigned) {
        if (category != 5) {
            if ((elementIndex - firstIndex != 0)
                    && !checkListContents1(elementIndex, course)) {
                lowerOrderCourseAssigned = false;       // A flag to notify that a course like A in list A to D is assigned and B,C,D can now be assigned.
                // Check if current course is not the first course. If it is not the first course
                // -check if any of the earlier course is taken. If yes, assign current course right away as this
                // will fulfill the pre-requisite of at-least 2 courses/category.

                // eg. course = C; List has A,B,C,D; index(C) - index(A) != 0.
                // AND check if courses prior to C are taken. If all are taken; assign C.
                // If not, put C in wait-list.
                //fetch all courses before current and check if any of them are taken.
                waitingCourses.add(course);
                courseQueued = true;
            } else {
                // Assign C
                if (!(!waitingCourses.isEmpty()
                        && lowerOrderCourseAssigned)) {
                    lowerOrderCourseAssigned = true;
                }
                coursesTaken.add(course);
                courseQueued = false;
                satisfyPreReq(category);            // Checks if 2 courses/category is satisfied or not (one at a time).
            }
        }
        return lowerOrderCourseAssigned;
    }

    /**
     * Assigns waiting courses.
     */
    public void assignWaitingCourses() {

        int categoryType = 0;
        // Check if the waiting list contains a course that can be assigned before the current course.
        while (!waitingCourses.isEmpty()) {
            if (!waitingCourses.isEmpty()) {
                waitingCourse = waitingCourses.get(0);
                if (waitingCourse.matches("[A-D]")) categoryType = 1;
                if (waitingCourse.matches("[E-H]")) categoryType = 2;
                if (waitingCourse.matches("[I-L]")) categoryType = 3;
                if (waitingCourse.matches("[M-P]")) categoryType = 4;
                if (categoryType == 1) elementIndex = category1.indexOf(waitingCourse);
                if (categoryType == 2) elementIndex = category2.indexOf(waitingCourse);
                if (categoryType == 3) elementIndex = category3.indexOf(waitingCourse);
                if (categoryType == 4) elementIndex = category4.indexOf(waitingCourse);
                if (checkListContents(elementIndex, waitingCourse)) {
                    coursesTaken.add(waitingCourse);
                    satisfyPreReq(categoryType);
                    waitingCourses.remove(waitingCourse); // Remove the waiting course that was just added to the coursesTaken list.
                    if (!coursesTaken.isEmpty() && ((coursesTaken.size() % 3) == 0)) {
                        semesterCount++;
                    }
                }
            }
        }
        //adjustSemesters();
    }

    /** Deprecated method.
     * This method helps in adjusting the semesters as we increase semester count by 1 when 3 courses are taken.
     * So if 10 courses are taken, the old logic says that the student had 3 semesters.
     * But infact there were 4 semesters as 1 course was in the fourth semester.
     */
    private void adjustSemesters() {
        int semCount = 0;
        int count = 0;

        for (int i = 1; i <= coursesTaken.size(); i++) {
            count++;
            if (count % 3 == 0) {
                semCount++;
            }
        }
        if ((count * 3) > semCount) {
            semCount++;
        }
        semesterCount = semCount;
    }

    /**
     * Check if a course is waiting to be assigned before assigning the current course to maintain assigning priority.
     * @param category
     * @param currentCourse
     * @return waitingCourseAssigned
     */
    public boolean checkWaitingList(int category, String currentCourse) {

        boolean waitingCourseAssigned = false;
        int index = 0;
        if (!waitingCourses.isEmpty()) {
            // Check if a course can be assigned from the waiting list, in case there's a lower order course assigned from that category.
            String nextCourse = "";
            int elementIndex = -1;
            for (int i = 0; i < waitingCourses.size(); i++) {
                if (category == 1) {
                    index = category1.indexOf(waitingCourses.get(i));
                    if (index == category1.size() - 1) {
                        nextCourse = category1.get(index);
                    } else {
                        nextCourse = category1.get(index + 1);
                    }
                    elementIndex = category1.indexOf(waitingCourses.get(i));
                } else if (category == 2) {
                    index = category2.indexOf(waitingCourses.get(i));
                    if (index == category2.size() - 1) {
                        nextCourse = category2.get(index);
                    } else {
                        nextCourse = category2.get(index + 1);
                    }
                    elementIndex = category2.indexOf(waitingCourses.get(i));
                } else if (category == 3) {
                    index = category3.indexOf(waitingCourses.get(i));
                    if (index == category3.size() - 1) {
                        nextCourse = category3.get(index);
                    } else {
                        nextCourse = category3.get(index + 1);
                    }
                    elementIndex = category3.indexOf(waitingCourses.get(i));
                } else if (category == 4) {
                    index = category4.indexOf(waitingCourses.get(i));
                    if (index == category4.size() - 1) {
                        nextCourse = category4.get(index);
                    } else {
                        nextCourse = category4.get(index + 1);
                    }
                    elementIndex = category4.indexOf(waitingCourses.get(i));
                }
                if (!nextCourse.equals("") && waitingCourses.get(i).equals(nextCourse)) {
                    if (checkListContents1(elementIndex, waitingCourses.get(i))) {
                        coursesTaken.add(waitingCourses.get(i));
                        waitingCourses.remove(waitingCourses.get(i));
                        waitingCourseAssigned = true;
                    }
                }
            }
        }
        return waitingCourseAssigned;
    }

    /**
     * Checks if the 2 course prerequisite is satisfied for a particular category.
     * A temporary list (intersectedList) is created by copying the current coursesTaken list.
     * This list then retains the elements from a particular category courses.
     * So we have an intersection of coursesTaken with the fixed number of courses with a particular category.
     * This yields us only the courses that are taken. Then we check if the size is greater or equal to 2.
     * If it is; the category prerequisite is satisfied.
     * @param categoryIn
     */
    private void satisfyPreReq(int categoryIn) {
        List<String> intersectedList = new ArrayList<String>(coursesTaken);
        switch (categoryIn) {
            case 1:
                intersectedList.retainAll(category1);
                if (intersectedList.size() >= 2) category1Satisfied = true;
                break;
            case 2:
                intersectedList.retainAll(category2);
                if (intersectedList.size() >= 2) category2Satisfied = true;
                break;
            case 3:
                intersectedList.retainAll(category3);
                if (intersectedList.size() >= 2) category3Satisfied = true;
                break;
            case 4:
                intersectedList.retainAll(category4);
                if (intersectedList.size() >= 2) category4Satisfied = true;
                break;
            case 5:
                intersectedList.retainAll(category5);
                if (intersectedList.size() >= 2) category5Satisfied = true;
                break;
        }
    }

    /**
     * This method is used to check if any course earlier to the current(to be assigned) one is taken or not.
     * @return False; no course taken
     *         True; a course is taken.
     */
    private boolean checkListContents1(int elementIndexIn, String course) {

        boolean status = false;
        int category = 0;
        int innerCategory = 0;
        if (course.matches("[A-D]")) category = 1;
        if (course.matches("[E-H]")) category = 2;
        if (course.matches("[I-L]")) category = 3;
        if (course.matches("[M-P]")) category = 4;

        for (String crs: coursesTaken) {
            if (crs.matches("[A-D]")) innerCategory = 1;
            if (crs.matches("[E-H]")) innerCategory = 2;
            if (crs.matches("[I-L]")) innerCategory = 3;
            if (crs.matches("[M-P]")) innerCategory = 4;
            if (innerCategory == category && !crs.equals(course)) {
                if (innerCategory == 1) {
                   if (category1.indexOf(crs) < elementIndexIn) status = true;
                }
                if (innerCategory == 2) {
                    if (category2.indexOf(crs) < elementIndexIn) status = true;
                }
                if (innerCategory == 3) {
                    if (category3.indexOf(crs) < elementIndexIn) status = true;
                }
                if (innerCategory == 4) {
                    if (category4.indexOf(crs) < elementIndexIn) status = true;
                }

                // Check if it is taken or not
            }
        }
        return status;
    }

    private boolean checkListContents(int elementIndexIn, String course) {

        boolean status = false;
        for (String crs: coursesTaken) {
            if (coursesTaken.indexOf(crs) < elementIndexIn && !crs.equals(course)) {
                // Check if it is taken or not
                status = true;
            }
        }
        return status;
    }

    /**
     *  File line is passed and broken into tokens.
     * @param fileLineIn
     */
    private void addCoursesInList(String fileLineIn) {

        if (!fileLineIn.equals("") && !fileLineIn.isEmpty()) {
            String[] tokens = fileLineIn.split(":| :|: ", 2);
            setbNumber(Integer.parseInt(tokens[0]));
            String courses = tokens[1].trim();
            String[] courseTokens = courses.split(" ");
            StudentManager.courses = new ArrayList<String>(Arrays.asList(courseTokens));
        }
    }

    /**
     * This method fetches the allowed courses from each category and stores them in separate lists.
     * These lists are then used for comparison and pre-requisite handling.
     */
    private void segregateCourses() {

        setCategory1();
        setCategory2();
        setCategory3();
        setCategory4();
        setCategory5();
    }

    public static int getbNumber() {
        return bNumber;
    }

    public static void setbNumber(int bNumber) {
        StudentManager.bNumber = bNumber;
    }

    public static List<String> getCoursesTaken() {
        return coursesTaken;
    }

    public static ArrayList<String> getCourses() {
        return courses;
    }

    public List<String> getWaitingCourses() {
        return waitingCourses;
    }

    public void setWaitingCourses(List<String> waitingCoursesIn) {
        waitingCourses = waitingCoursesIn;
    }

    public List<String> getCategory1() {
        return category1;
    }

    public boolean isCategory1Satisfied() {
        return category1Satisfied;
    }

    public void setCategory1Satisfied(boolean category1satisfiedIn) {
        category1Satisfied = category1satisfiedIn;
    }

    public boolean isCategory2Satisfied() {
        return category2Satisfied;
    }

    public void setCategory2Satisfied(boolean category2satisfiedIn) {
        category2Satisfied = category2satisfiedIn;
    }

    public boolean isCategory3Satisfied() {
        return category3Satisfied;
    }

    public void setCategory3Satisfied(boolean category3satisfiedIn) {
        category3Satisfied = category3satisfiedIn;
    }

    public boolean isCategory4Satisfied() {
        return category4Satisfied;
    }

    public void setCategory4Satisfied(boolean category4satisfiedIn) {
        category4Satisfied = category4satisfiedIn;
    }

    public boolean isCategory5Satisfied() {
        return category5Satisfied;
    }

    public void setCategory5Satisfied(boolean category5satisfiedIn) {
        category5Satisfied = category5satisfiedIn;
    }

    public static int getSemesterCount() {
        return semesterCount;
    }

    public static void setSemesterCount(int semesterCount) {
        StudentManager.semesterCount = semesterCount;
    }

    public int getElementIndex() {
        return elementIndex;
    }

    public void setElementIndex(int elementIndexIn) {
        elementIndex = elementIndexIn;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndexIn) {
        firstIndex = firstIndexIn;
    }

    public String getWaitingCourse() {
        return waitingCourse;
    }

    public void setWaitingCourse(String waitingCourseIn) {
        waitingCourse = waitingCourseIn;
    }

    public boolean isLowerOrderCourseAssigned() {
        return lowerOrderCourseAssigned;
    }

    public void setLowerOrderCourseAssigned(boolean lowerOrderCourseAssignedIn) {
        lowerOrderCourseAssigned = lowerOrderCourseAssignedIn;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int categoryIn) {
        category = categoryIn;
    }

    public void setCategory1() {
        String[] arr1 = {"A","B","C","D"};
        category1 = new ArrayList<String>(Arrays.asList(arr1));
    }

    public List<String> getCategory2() {
        return category2;
    }

    public void setCategory2() {
        String[] arr2 = {"E","F","G","H"};
        category2 = new ArrayList<String>(Arrays.asList(arr2));
    }

    public List<String> getCategory3() {
        return category3;
    }

    public void setCategory3() {
        String[] arr3 = {"I","J","K","L"};
        category3 = new ArrayList<String>(Arrays.asList(arr3));
    }

    public List<String> getCategory4() {
        return category4;
    }

    public void setCategory4() {
        String[] arr4 = {"M","N","O","P"};
        category4 = new ArrayList<String>(Arrays.asList(arr4));
    }

    public List<String> getCategory5() {
        return category5;
    }

    public void setCategory5() {
        String[] arr5 = {"Q","R","S","T","U","V","W","X","Y","Z"};
        category5 = new ArrayList<String>(Arrays.asList(arr5));
    }

    public boolean isCourseQueued() {
        return courseQueued;
    }

    public void setCourseQueued(boolean courseQueuedIn) {
        this.courseQueued = courseQueuedIn;
    }

    @Override
    public String toString() {
        return "StudentManager{" +
                "waitingCourses=" + waitingCourses +
                ", elementIndex=" + elementIndex +
                ", firstIndex=" + firstIndex +
                ", waitingCourse='" + waitingCourse + '\'' +
                ", lowerOrderCourseAssigned=" + lowerOrderCourseAssigned +
                ", category=" + category +
                ", courseQueued=" + courseQueued +
                ", category1=" + category1 +
                ", category2=" + category2 +
                ", category3=" + category3 +
                ", category4=" + category4 +
                ", category5=" + category5 +
                ", category1Satisfied=" + category1Satisfied +
                ", category2Satisfied=" + category2Satisfied +
                ", category3Satisfied=" + category3Satisfied +
                ", category4Satisfied=" + category4Satisfied +
                ", category5Satisfied=" + category5Satisfied +
                '}';
    }
}
