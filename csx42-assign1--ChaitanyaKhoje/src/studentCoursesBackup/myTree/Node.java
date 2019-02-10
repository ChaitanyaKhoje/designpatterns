package studentCoursesBackup.myTree;

import studentCoursesBackup.util.Action;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Objects;

public class Node implements ObserverI, SubjectI, Cloneable {

    private int bNumber;
    private Set<String> courseNames = new HashSet<String>();
    private Node leftChild;
    private Node rightChild;
    private List<ObserverI> observers = new ArrayList<ObserverI>();

    public Node() {
    }

    @Override
    public void register(ObserverI observerIn) {
        observers.add(observerIn);
    }

    @Override
    public void delete(ObserverI observerIn) {
        observers.remove(observerIn);
    }

    @Override
    public void notifyListeners(String courseNameIn, Action actionIn) {
        for (ObserverI observer : observers) {
            observer.update(courseNameIn, actionIn);
        }
    }

    /**
     * Method overloading.
     * One updated course, other updates child node.
     * @param nodeIn
     * @param childIn
     */
    @Override
    public void notifyListeners(Node nodeIn, String childIn) {
        for (ObserverI observer : observers) {
            observer.update(nodeIn, childIn);
        }
    }

    /**
     * @param courseNameIn
     * @param actionIn
     */
    @Override
    public void update(String courseNameIn, Action actionIn) {
        if (actionIn == Action.INSERT) {
            courseNames.add(courseNameIn);
        } else if (actionIn == Action.DELETE) {
            courseNames.remove(courseNameIn);
        } else {
            throw new IllegalArgumentException("Invalid Action");
        }
    }

    /**
     * @param nodeIn
     * @param childIn
     */
    @Override
    public void update(Node nodeIn, String childIn) {
        switch (childIn) {
            case "LEFT":
                setLeftChild(nodeIn);
                break;
            case "RIGHT":
                setRightChild(nodeIn);
                break;
            default:
                throw new IllegalArgumentException("Invalid input");
        }
    }

    /**
     * @return bNumber A getter method for bNumber.
     */
    public int getbNumber() {
        return bNumber;
    }

    public void setbNumber(int BNumberIn) {
        bNumber = BNumberIn;
    }

    /**
     * @return courseNames A getter method for courseNames
     */
    public Set<String> getCourseNames() {
        return courseNames;
    }

    /**
     * Sets the coursename for a node.
     *
     * @param courseNamesIn
     */
    public void setCourseNames(Set<String> courseNamesIn) {
        courseNames = courseNamesIn;
    }

    /**
     * @return returns leftChild for a node.
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Sets left child for a node
     *
     * @param leftChildIn
     */
    public void setLeftChild(Node leftChildIn) {
        leftChild = leftChildIn;
    }

    /**
     * @return returns rightChild for a node.
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Sets the right child for a node.
     *
     * @param rightChildIn
     */
    public void setRightChild(Node rightChildIn) {
        rightChild = rightChildIn;
    }

    /**
     * @return returns the list of observers.
     */
    public List<ObserverI> getObservers() {
        return observers;
    }

    @Override
    public Node clone() {

        Node node = new Node();
        node.setbNumber(this.bNumber);
        node.setCourseNames(this.courseNames);
        node.setLeftChild(this.leftChild);
        node.setRightChild(this.rightChild);
        return node;
    }

    @Override
    public String toString() {
        return "bNumber=" + bNumber +
                ", courseNames=" + courseNames +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return bNumber == node.bNumber &&
                Objects.equals(courseNames, node.courseNames) &&
                Objects.equals(leftChild, node.leftChild) &&
                Objects.equals(rightChild, node.rightChild) &&
                Objects.equals(observers, node.observers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bNumber, courseNames, leftChild, rightChild, observers);
    }
}
