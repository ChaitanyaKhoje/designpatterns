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

[Date: ] -- 02/13/2018

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

Time complexity:
Average case for insertion - O(nlogn)
Worst case - O(n)

Space complexity:
Worst case - O(n)

My observer pattern implementation:

Node is both the Observer and the Subject in this project.
The ObserverI interface has 2 methods with same names as below;

public interface ObserverI {

    void update(String courseName, Action action);
    void update(Node node, String child);
}

I have implemented method overloading here as one update() method is used to update
course name for the node and other updates the child node. The implementations of these methods are
in Node class.

The SubjectI interface has 4 methods as shown below;

public interface SubjectI {

    void register(ObserverI observer);
    void delete(ObserverI observer);
    void notifyListeners(String courseName, Action action);
    void notifyListeners(Node node, String child);
}

The register method is used to register an observer while node insertion in insert() method.
delete method is declared but not implemented. It can be used for future purpose.
notifyListeners is an overloaded method where one updates the course name for an observer and the
other updates the child nodes.

The observer pattern is used to delete nodes from the back up trees as it is basically an update to the
back ups if a node is deleted from the original tree.
Courses are updated using the observer pattern (add/delete).

Justification for data structures:

A HashSet is used for courses to avoid duplicity.
A List of nodes is used after in-order traversal for storing the results to be printed.
An ArrayList is used while doing in-order traversal. This list is passed to a method named 'helper'
which does the part of recurssion.

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).



