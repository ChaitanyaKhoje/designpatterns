package genericCheckpointing.util;

import java.util.Objects;

public class MyAllTypesSecond extends SerializableObject {

    private double myDoubleT;
    private double myOtherDoubleT;
    private float myFloatT;
    private short myShortT;
    private char myCharT;

    public MyAllTypesSecond() {

        myDoubleT = 0;
        myOtherDoubleT = 0;
        myFloatT = 0;
        myShortT = 0;
        myCharT = 0;
    }

    public MyAllTypesSecond(double myDoubleTIn, double myOtherDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn) {

        myDoubleT = myDoubleTIn;
        myOtherDoubleT = myOtherDoubleTIn;
        myFloatT = myFloatTIn;
        myShortT = myShortTIn;
        myCharT = myCharTIn;
    }

    public double getmyDoubleT() {
        return myDoubleT;
    }

    public void setmyDoubleT(double myDoubleTIn) {
        myDoubleT = myDoubleTIn;
    }

    public double getmyOtherDoubleT() {
        return myOtherDoubleT;
    }

    public void setmyOtherDoubleT(double myOtherDoubleTIn) {
        myOtherDoubleT = myOtherDoubleTIn;
    }

    public float getmyFloatT() {
        return myFloatT;
    }

    public void setmyFloatT(float myFloatTIn) {
        myFloatT = myFloatTIn;
    }

    public short getmyShortT() {
        return myShortT;
    }

    public void setmyShortT(short myShortTIn) {
        myShortT = myShortTIn;
    }

    public char getmyCharT() {
        return myCharT;
    }

    public void setmyCharT(char myCharTIn) { myCharT = myCharTIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAllTypesSecond that = (MyAllTypesSecond) o;
        return Double.compare(that.myDoubleT, myDoubleT) == 0 &&
                Double.compare(that.myOtherDoubleT, myOtherDoubleT) == 0 &&
                Float.compare(that.myFloatT, myFloatT) == 0 &&
                myShortT == that.myShortT &&
                myCharT == that.myCharT;
    }

    @Override
    public int hashCode() {

        return Objects.hash(myDoubleT, myOtherDoubleT, myFloatT, myShortT, myCharT);
    }

    @Override
    public String toString() {
        return "MyAllTypesSecond{" +
                "myDoubleT=" + myDoubleT +
                ", myOtherDoubleT=" + myOtherDoubleT +
                ", myFloatT=" + myFloatT +
                ", myShortT=" + myShortT +
                ", myCharT=" + myCharT +
                '}';
    }
}
