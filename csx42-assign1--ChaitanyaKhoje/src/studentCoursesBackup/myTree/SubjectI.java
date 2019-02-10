package studentCoursesBackup.myTree;

import studentCoursesBackup.util.Action;

public interface SubjectI {

    void register(ObserverI observer);
    void delete(ObserverI observer);
    void notifyListeners(String courseName, Action action);
    void notifyListeners(Node node, String child);
}
