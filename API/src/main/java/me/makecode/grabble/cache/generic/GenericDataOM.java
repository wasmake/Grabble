package me.makecode.grabble.cache.generic;

public class GenericDataOM<T,S> {
    private T objetManager;
    private S dataStore;
    public GenericDataOM(T objectManager){
        this.objetManager = objectManager;
    }

    public void setDataStore(S dataStore){
        this.dataStore = dataStore;
    }

    public T getObjectManager(){
        return this.objetManager;
    }

    public S getDataStore(){
        return this.dataStore;
    }

}
