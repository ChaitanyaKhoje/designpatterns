Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile:
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To create java doc:
ant -buildfile src/build.xml doc


-----------------------------------------------------------------------
## To run by specifying arguments from command line
## We will use this to run your code
## Keep the input and delete files under studentOrientation (along with src and README)
ant -buildfile src/build.xml run
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

[Date: ] -- 04/10/2018

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

1)Each of the activity takes different Enums as input. For an instance, attend lecture takes ATTEND_LECTURE_CS240,
    ATTEND_LECTURE_CS350 as input.

2) The output of the Duration, cost, efforts etc. depends on the Enum selected by the user.

3)We have just one common ENUM OrientationItinerary for all the activities.

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

