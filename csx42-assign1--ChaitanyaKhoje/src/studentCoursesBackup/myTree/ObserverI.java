package studentCoursesBackup.myTree;

import studentCoursesBackup.util.Action;

public interface ObserverI {

    void update(String courseName, Action action);
    void update(Node node, String child);
}
