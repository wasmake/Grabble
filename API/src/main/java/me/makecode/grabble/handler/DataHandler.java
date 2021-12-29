package me.makecode.grabble.handler;

import me.makecode.grabble.abstraction.DataOM;
import me.makecode.grabble.abstraction.GenericDatastorage;
import me.makecode.grabble.object.DataAbstract;

import java.util.List;

public interface DataHandler {
    void connect(String... args);
    void registerClass(Class... classes);
    void save(Object clazz);
    void delete(Object clazz);
    List<DataAbstract> getAll(Class<?> clazz);
    DataAbstract getSpecific(Class<?> clazz, String parameter, Object identifier);
    boolean isConnected();
    DataOM getDataOM();
    GenericDatastorage getDataStorage();
}
