Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile:
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=firstarg -Darg1=SECOND -Darg2=THIRD -Darg3=THIRD -Darg4=THIRD

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.

[Date: ] -- 03/13/2018

-----------------------------------------------------------------------

WAIT-LIST LOGIC IMPLEMENTATION:

As everyone has their own interpretation of the waiting-list logic, here's how I have implemented it.

For instance,
if our input of courses is as follows;

D C B A

Here, D is highest order in this category. So D is added to the wait-list because the input file may or may not contain A/B/C.
Next the course C has to wait similarly for A and/or B.
Next B has to wait for A.
A is the first course in this category hence can be assigned if came across.
Now the waitlist is checked everytime a new course comes in.
If the waitlist has D at first index.
D will be assigned after A, then comes C and then B.
So the output will be: A D C B

Steps:
1st iteration: D comes in. D goes into wait-list.
2nd iteration: C comes in. C goes into wait-list.
3rd iteration: B comes in. B goes into wait-list.
4th iteration: A comes in. A is assigned. State is changed (to CoreCourseAssigned in this example).

Now in this example; D,C,B remain in the wait-list until the whole input ArrayList is traversed till the last element comes which is A.
When A is assigned. The input ArrayList has reached its end, all that remains is the waitlist. So the waitlist is now iterated and the courses are assigned.
Now the first course in the waitlist is D, so it has the highest priority amongst other waiting courses.
So after A comes D. Now here, the 2 course/category requirement is satisfied for that particular category.
C and B are assigned after D and can be considered as extra courses.

Another example;
Input: P M O F I G Z...

Iterations:
1. P comes in. P in waitlist.
2. M comes in. M assigned.
3. O comes in. Checked waitlist, got P waiting. Assigned P.
4. Again O comes in. O assigned.
5. F comes in. F in waitlist.
6. I comes in. I assigned.
7. G comes in. G in waitlist.
8. Z comes in. Z assigned.
9. F popped from waitlist. F assigned.
10. G popped from waitlist. G assigned.

Doesn't meet 2 courses/category and/or 10 courses in total. Hence cannot graduate.

Output: M P O I Z F G Cannot Graduate!

--------------------

STATE PATTERN implementation:

This assignment deals with a student's graduation process in which we deal with categories of courses he takes as he graduates.

In my design;
I've made 5 states. The state changes as different courses are passed.

Before talking about the states in my design, let's talk about the methods which help in changing the states;
The CoursePlannerStateI interface has two methods;
1. enroll (assigns courses and adds courses to the waiting list)
2. graduate (the student graduates after satisfaction of prerequisites and applying for sufficient courses(i.e 10))

Note: There's an enum named Action which contains some actions related to what just happened while assigning a course.
This helps in changing the state accordingly.
enum Action contains the following actions;
CORE_ASSIGNED,
ELECTIVE_ASSIGNED,
QUEUED,
NOCOURSES,
PREREQ_VIOLATED,
PREREQ_SATISFIED,
WAITING_COURSE_ASSIGNED,
NOACTION

The actions are self-explanatory.
The WAITING_COURSE_ASSIGNED and CORE_ASSIGNED are used for similar purposes 
(whenever a core course i.e a course from first 4 categories is assigned, the action is set to CORE_ASSIGNED 
-and if a waiting list core course is assigned the action is set to WAITING_COURSE_ASSIGNED but both of these 
-actions yield to the same state change i.e the CoreCourseState)

CourseManager class:
CourseManager is the context class which decides the state changes.
The methods state.enroll and state.graduate are called in this class.


The 5 states are as follow;

1. NewStudentState
This state is the base/default state in which the student starts. 
The state variable (which keeps changing) is initialized by this state.

When the enroll() method is called;
It checks if a particular course is assigned or not (also checks its type; elective or core),
if the course is assigned(actions: CORE_ASSIGNED, ELECTIVE_ASSIGNED, WAITING_COURSE_ASSIGNED); the state changes to CoreCourseState or ElectiveCourseState (first 4 categories in CoreCourseState and 5th category in ElectiveCourseState).
If a course is QUEUED (added to the waiting list); the state remains the same (i.e NewStudentState).

When the graduate() method is called from this state;
If the action passed as parameter to this method is CORE_ASSIGNED/WAITING_COURSE_ASSIGNED, the CoreCourseState is set.
If the action passed as parameter to this method is ELECTIVE_ASSIGNED, the ElectiveCourseState is set.
Here we don't need to do anything more as this is the NewStudentState, so the student cannot graduate unless and until the prereqs are satisfied.

2. CoreCourseState
When a core course, that is, a course from any of the first 4 categories is assigned. The state changes to CoreCourseState.

When the enroll method is called;
State changes to ElectiveCourseState on checking if the action is ELECTIVE_ASSIGNED.
If a core course is assigned while in this state, the state remains the same.

When the graduate method is called;
Flags for all the categories are checked. If they are set (which means the requirement of 2 courses/category is satisfied), and the PREREQ_SATISFIED
-action is set; The state changes to GraduatedStudentState.

3. ElectiveCourseState

When the enroll method is called;
If the action is CORE_ASSIGNED/WAITING_COURSE_ASSIGNED, The state changes to CoreCourseState.
If an elective is assigned (action: ELECTIVE_ASSIGNED), the state remains the same.

When the graduate method is called;
Same checks like CoreCourseState's graduate method are made and the state is changed to GraduatedStudentState if conditions are satisfied.

4. GraduateStudentState

This state is the last state of the graduation process.
So both the methods; enroll and graduate, keep this state maintained.


5. CannotGraduateState

If the pre-requisites are not satisfied, the action is set to PREREQ_VIOLATED.
Both the enroll and graduate methods in this state set the state to CannotGraduateState.

------------

So the basic flow of the state pattern in my assignment is as follows;

When the program starts, i.e when no courses are assigned to the student, the state is set to NewStudentState.
When a course comes in; depending on core/elective type, the corresponding state is set, CoreCourseState or ElectiveCourseState.
If the student meets the criteria of 10 courses and 2 courses/category and does not violate the pre-requisites set for first 4 categories; the state is changed to GraduatedStudentState.
If the pre-requisites are violated and/or the student doesn't take required number of courses, the student does not graduate, which means the state is set to CannotGraduateState.
Note that the states CoreCourseState and ElectiveCourseState keep on changing as per the course input and finally land onto GraduatedStudentState or CannotGraduateState.


Justification for data structures:

ArrayList to store the input file.
Arrays to handle tokens while splitting


-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).
