package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

public interface SerStrategy {

    void processInput(SerializableObject sObject);
    SerializableObject processFile();
}
