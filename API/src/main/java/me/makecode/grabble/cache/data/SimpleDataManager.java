package me.makecode.grabble.cache.data;

import me.makecode.grabble.cache.handler.SimpleDataHandler;
import me.makecode.grabble.exception.DataInvalidIdentifierException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleDataManager<T> {
    private Map<Object, DataAbstract> dataCache;
    private String parameter;
    private T data;
    private Class<?> from;
    private SimpleDataHandler dataHandler;
    private DataLoadStrategy dataLoadStrategy;
    private boolean cache = false;

    public SimpleDataManager(T data, String parameter, SimpleDataHandler dataHandler, boolean cache){
        dataCache = new HashMap<>();
        this.data = data;
        this.parameter = parameter;
        this.cache = cache;
        this.dataHandler = dataHandler;
        dataHandler.registerClass(data.getClass());
        dataHandler.getDataManagerList().add(this);
    }

    public SimpleDataManager setFrom(Class<?> clazz){
        this.from = clazz;
        return this;
    }

    public SimpleDataManager loadOperation(DataLoadStrategy dataLoadStrategy){
        this.dataLoadStrategy = dataLoadStrategy;
        return this;
    }

    public DataAbstract load(Object object){
        try {
            if(from.isAssignableFrom(object.getClass())){
                return get(dataLoadStrategy.execute(object));
            } else throw new DataInvalidIdentifierException(data.getClass().getSimpleName() + " with value " + object);
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
        Object identifierFromObject = dataLoadStrategy.execute(object);
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

    public DataAbstract getViaLoad(Object identifier){
        return load(identifier);
    }

    public DataAbstract get(Object identifier){
        if(cache) return getCache(identifier);
        return getFromDB(identifier);
    }

    public Set<DataAbstract> getCache(){
        if(cache) return new HashSet<>(dataCache.values());
        return new HashSet<>();
    }

    public DataAbstract create(Object identifier){
        return getNewInstance(identifier);
    }

    public DataAbstract getFromDB(Object identifier){
        DataAbstract dataAbstract = dataHandler.getSpecific(this.data.getClass(), this.parameter, identifier);
        if(dataAbstract == null){
            dataAbstract = getNewInstance(identifier);
        }
        return dataAbstract;
    }

    public List<DataAbstract> getAllFromDB(){
        List<DataAbstract> dataAbstracts = dataHandler.getAll(this.data.getClass());
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
            Constructor constructor = this.data.getClass().getConstructor(String.class);
            if(!constructor.isAccessible()) constructor.setAccessible(true);
            dataAbstract = (DataAbstract) this.data.getClass().getConstructor(String.class).newInstance(identifier);
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
        return this.data.getClass();
    }

}
