package genericCheckpointing.util;

import java.util.Objects;

public class MyAllTypesFirst extends SerializableObject {

    private int myInt;
    private long myLong;
    private String myString;
    private boolean myBool;
    private int myOtherInt;
    private long myOtherLong;

    public MyAllTypesFirst() {

        myInt = 0;
        myLong = 0;
        myString = "Design Patterns";
        myBool = false;
        myOtherInt = 0;
        myOtherLong = 0;
    }

    public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn, long myOtherLongIn) {

        myInt = myIntIn;
        myLong = myLongIn;
        myString = myStringIn;
        myBool = myBoolIn;
        myOtherInt = myOtherIntIn;
        myOtherLong = myOtherLongIn;
    }

    public int getmyInt() { return myInt; }

    public void setmyInt(int myIntIn) {
        myInt = myIntIn;
    }

    public long getmyLong() { return myLong; }

    public void setmyLong(long myLongIn) {
        myLong = myLongIn;
    }

    public String getmyString() {
        return myString;
    }

    public void setmyString(String myStringIn) {
        myString = myStringIn;
    }

    public boolean ismyBool() {
        return myBool;
    }

    public void setmyBool(boolean myBoolIn) {
        myBool = myBoolIn;
    }

    public int getmyOtherInt() {
        return myOtherInt;
    }

    public void setmyOtherInt(int myOtherIntIn) {
        myOtherInt = myOtherIntIn;
    }

    public long getmyOtherLong() {
        return myOtherLong;
    }

    public void setmyOtherLong(long myOtherLongIn) {
        myOtherLong = myOtherLongIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAllTypesFirst that = (MyAllTypesFirst) o;
        return myInt == that.myInt &&
                myLong == that.myLong &&
                myBool == that.myBool &&
                myOtherInt == that.myOtherInt &&
                myOtherLong == that.myOtherLong &&
                Objects.equals(myString, that.myString);
    }

    @Override
    public int hashCode() {

        return Objects.hash(myInt, myLong, myString, myBool, myOtherInt, myOtherLong);
    }

    @Override
    public String toString() {

        return "MyAllTypesFirst{" +
                "myInt=" + myInt +
                ", myLong=" + myLong +
                ", myString='" + myString + '\'' +
                ", myBool=" + myBool +
                ", myOtherInt=" + myOtherInt +
                ", myOtherLong=" + myOtherLong +
                '}';
    }
}
