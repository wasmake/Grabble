package me.makecode.grabble.abstraction;

import me.makecode.grabble.handler.DataInvalidIdentifierException;
import me.makecode.grabble.handler.SimpleDataHandler;
import me.makecode.grabble.object.DataAbstract;
import me.makecode.grabble.object.DataLoad;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DataManager {
    private HashMap<Object, DataAbstract> dataCache;
    private String parameter;
    private Class<?> data, from;
    private SimpleDataHandler dataHandler;
    private DataLoad dataLoad;
    private boolean cache = false, leave = false, join = false, start = false, disable = true;

    public DataManager(Class<?> data, String parameter, SimpleDataHandler dataHandler, boolean cache){
        dataCache = new HashMap<>();
        this.data = data;
        this.parameter = parameter;
        this.cache = cache;
        this.dataHandler = dataHandler;
        dataHandler.registerClass(data);
        dataHandler.getDataManagerList().add(this);
    }

    public DataManager setFrom(Class<?> clazz){
        this.from = clazz;
        return this;
    }

    public DataManager loadOperation(DataLoad dataLoad){
        this.dataLoad = dataLoad;
        return this;
    }

    public DataAbstract load(Object object){
        try {
            if(from.isAssignableFrom(object.getClass())){
                return get(dataLoad.execute(object));
            } else throw new DataInvalidIdentifierException(data.getSimpleName() + " with value " + object);
        } catch (DataInvalidIdentifierException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public void removeDataAbstract(DataAbstract dataAbstract){
        dataHandler.delete(dataAbstract);
    }

    public void save(Object object){
        saveDataAbstract(load(object));
    }

    public void saveAndClose(Object object){
        Object identifierFromObject = dataLoad.execute(object);
        saveDataAbstract(load(object));
        if(cache) dataCache.remove(identifierFromObject);
    }

    public void saveWithIdentifier(Object identifier){
        dataHandler.save(get(identifier));
    }

    public void saveDataAbstract(DataAbstract dataAbstract){
        dataHandler.save(dataAbstract);
    }

    public void saveAll(){
        for(DataAbstract dataAbstract : dataCache.values()) dataHandler.save(dataAbstract);
    }

    public DataManager setCacheParams(boolean leave, boolean join, boolean start, boolean disable){
        this.leave = leave;
        this.join = join;
        this.start = start;
        this.disable = disable;
        return this;
    }

    public DataAbstract getViaLoad(Object identifier){
        return load(identifier);
    }

    public DataAbstract get(Object identifier){
        if(cache) return getCache(identifier);
        return getFromDB(identifier);
    }

    public HashSet<DataAbstract> getCache(){
        if(cache) return new HashSet<>(dataCache.values());
        return new HashSet<>();
    }

    public DataAbstract create(Object identifier){
        return getNewInstance(identifier);
    }

    public DataAbstract getFromDB(Object identifier){
        DataAbstract dataAbstract = dataHandler.getSpecific(this.data, this.parameter, identifier);
        if(dataAbstract == null){
            dataAbstract = getNewInstance(identifier);
        }
        return dataAbstract;
    }

    public List<DataAbstract> getAllFromDB(){
        List<DataAbstract> dataAbstracts = dataHandler.getAll(this.data);
        if(cache) {
            for(DataAbstract dataAbstract : dataAbstracts){
                this.dataCache.put(dataAbstract.getId(), dataAbstract);
            }
        }
        return dataAbstracts;
    }

    public DataAbstract getNewInstance(Object identifier){
        DataAbstract dataAbstract = null;
        try {
            Constructor constructor = this.data.getConstructor(String.class);
            if(!constructor.isAccessible()) constructor.setAccessible(true);
            dataAbstract = (DataAbstract) this.data.getConstructor(String.class).newInstance(identifier);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return dataAbstract;
    }

    public DataAbstract getCache(Object identifier){
        if(!dataCache.containsKey(identifier)){
            dataCache.put(identifier, getFromDB(identifier));
        }
        return dataCache.get(identifier);
    }

    public Class<?> getData(){
        return this.data;
    }

}
