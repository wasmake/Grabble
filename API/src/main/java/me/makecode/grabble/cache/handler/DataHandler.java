package me.makecode.grabble.cache.handler;

import me.makecode.grabble.cache.generic.GenericDataOM;
import me.makecode.grabble.cache.generic.GenericDataStorage;
import me.makecode.grabble.cache.data.DataAbstract;

import java.util.List;

public interface DataHandler {
    void connect(String... args);
    void registerClass(Class... classes);
    void save(Object clazz);
    void delete(Object clazz);
    List<DataAbstract> getAll(Class<?> clazz);
    DataAbstract getSpecific(Class<?> clazz, String parameter, Object identifier);
    boolean isConnected();
    GenericDataOM getDataOM();
    GenericDataStorage getDataStorage();
}
