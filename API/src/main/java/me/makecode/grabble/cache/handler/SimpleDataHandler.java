package me.makecode.grabble.cache.handler;

import me.makecode.grabble.cache.data.SimpleDataManager;
import me.makecode.grabble.cache.data.DataAbstract;

import java.util.HashSet;
import java.util.Set;

public abstract class SimpleDataHandler implements DataHandler {

    private Set<SimpleDataManager> simpleDataManagerList;

    public SimpleDataHandler(){
        simpleDataManagerList = new HashSet<>();
    }

    public DataAbstract create(Class<?> data, Object identifier){
        for(SimpleDataManager simpleDataManager : simpleDataManagerList){
            if(simpleDataManager.getData().equals(data)){
                return simpleDataManager.create(identifier);
            }
        }
        return null;
    }

    public Set<DataAbstract> getCache(Class<?> data){
        for(SimpleDataManager simpleDataManager : simpleDataManagerList){
            if(simpleDataManager.getData().equals(data)){
                return simpleDataManager.getCache();
            }
        }
        return null;
    }

    public DataAbstract getViaLoad(Class<?> data, Object identifier){
        for(SimpleDataManager simpleDataManager : simpleDataManagerList){
            if(simpleDataManager.getData().equals(data)){
                return simpleDataManager.getViaLoad(identifier);
            }
        }
        return null;
    }

    public SimpleDataManager getDataManager(Class<?> data){
        for(SimpleDataManager simpleDataManager : simpleDataManagerList){
            if(simpleDataManager.getData().equals(data)){
                return simpleDataManager;
            }
        }
        return null;
    }

    public DataAbstract get(Class<?> data, Object identifier){
        for(SimpleDataManager simpleDataManager : simpleDataManagerList){
            if(simpleDataManager.getData().equals(data)){
                return simpleDataManager.get(identifier);
            }
        }
        return null;
    }

    public Set<SimpleDataManager> getDataManagerList() {
        return simpleDataManagerList;
    }
}

