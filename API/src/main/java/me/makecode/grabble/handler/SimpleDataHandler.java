package me.makecode.grabble.handler;

import me.makecode.grabble.abstraction.DataManager;
import me.makecode.grabble.object.DataAbstract;

import java.util.HashSet;
import java.util.Set;

public abstract class SimpleDataHandler implements DataHandler {

    private Set<DataManager> dataManagerList;

    public SimpleDataHandler(){
        dataManagerList = new HashSet<>();
    }

    public Set<DataManager> unloadLeave(){
        Set<DataManager> managersList = new HashSet<>();
        for(DataManager dataManager : dataManagerList){
            if(dataManager.isLeave()) managersList.add(dataManager);
        }
        return managersList;
    }

    public Set<DataManager> unloadDisable(){
        Set<DataManager> managersList = new HashSet<>();
        for(DataManager dataManager : dataManagerList){
            if(dataManager.isDisable()) managersList.add(dataManager);
        }
        return managersList;
    }

    public Set<DataManager> loadJoin(){
        Set<DataManager> managersList = new HashSet<>();
        for(DataManager dataManager : dataManagerList){
            if(dataManager.isJoin()) managersList.add(dataManager);
        }
        return managersList;
    }

    public Set<DataManager> loadStart(){
        Set<DataManager> managersList = new HashSet<>();
        for(DataManager dataManager : dataManagerList){
            if(dataManager.isStart()) managersList.add(dataManager);
        }
        return managersList;
    }

    public DataAbstract create(Class<?> data, Object identifier){
        for(DataManager dataManager : dataManagerList){
            if(dataManager.getData().equals(data)){
                return dataManager.create(identifier);
            }
        }
        return null;
    }

    public Set<DataAbstract> getCache(Class<?> data){
        for(DataManager dataManager : dataManagerList){
            if(dataManager.getData().equals(data)){
                return dataManager.getCache();
            }
        }
        return null;
    }

    public DataAbstract getViaLoad(Class<?> data, Object identifier){
        for(DataManager dataManager : dataManagerList){
            if(dataManager.getData().equals(data)){
                return dataManager.getViaLoad(identifier);
            }
        }
        return null;
    }

    public DataManager getDataManager(Class<?> data){
        for(DataManager dataManager : dataManagerList){
            if(dataManager.getData().equals(data)){
                return dataManager;
            }
        }
        return null;
    }

    public DataAbstract get(Class<?> data, Object identifier){
        for(DataManager dataManager : dataManagerList){
            if(dataManager.getData().equals(data)){
                return dataManager.get(identifier);
            }
        }
        return null;
    }

    public Set<DataManager> getDataManagerList() {
        return dataManagerList;
    }
}

