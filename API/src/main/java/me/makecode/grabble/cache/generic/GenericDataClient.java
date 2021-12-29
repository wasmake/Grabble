package me.makecode.grabble.cache.generic;

public class GenericDataClient<T> {
    private final T dataClient;
    public GenericDataClient(T dataClient){
        this.dataClient = dataClient;
    }

    public T getDataClient() {
        return dataClient;
    }
}