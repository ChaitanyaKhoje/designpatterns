package genericCheckpointing.util;

import java.util.List;

public class Comparator {

    public int getMismatchedCount(List<SerializableObject> serializableObjects, List<SerializableObject> deserializedObjects) {

        int mismatchedObjects = 0;
        for(int k=0; k < serializableObjects.size(); k++) {
            if(!serializableObjects.get(k).equals(deserializedObjects.get(k))) {
                mismatchedObjects++;
            }
        }
        return mismatchedObjects;
    }

    @Override
    public String toString() {
        return "Comparator{}";
    }
}
