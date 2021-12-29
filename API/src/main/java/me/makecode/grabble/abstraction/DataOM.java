package me.makecode.grabble.abstraction;

public class DataOM {
    private Object objetManager;
    private Object dataStore;
    public DataOM(Object objectManager){
        this.objetManager = objectManager;
    }

    public void setDataStore(Object dataStore){
        this.dataStore = dataStore;
    }

    public Object getDataStore(){
        return this.dataStore;
    }

    public Object getObjectManager(){
        return this.objetManager;
    }

}
