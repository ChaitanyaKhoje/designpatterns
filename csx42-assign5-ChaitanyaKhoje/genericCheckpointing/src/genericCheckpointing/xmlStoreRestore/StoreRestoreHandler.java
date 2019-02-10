package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.server.SerStrategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.XMLDeserialization;
import genericCheckpointing.util.XMLSerialization;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StoreRestoreHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        SerializableObject deserializeObj = null;
        if(method.getName().equals("writeObj")) {
            serializeData((SerializableObject) args[0], new XMLSerialization());
        } else if (method.getName().equals("readObj")) {
            deserializeObj = deserializeData(new XMLDeserialization());
        }
        return deserializeObj;
    }

    public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
        sStrategy.processInput(sObject);
    }

    public SerializableObject deserializeData(SerStrategy sStrategy) {
        return sStrategy.processFile();
    }

    @Override
    public String toString() {
        return "StoreRestoreHandler{}";
    }
}
