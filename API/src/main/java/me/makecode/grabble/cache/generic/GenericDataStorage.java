package me.makecode.grabble.cache.generic;

public class GenericDataStorage<T> {
    private T dataSource;
    public GenericDataStorage(T dataSource){
        this.dataSource = dataSource;
    }

    public T getDataSource(){
        return dataSource;
    }
}
