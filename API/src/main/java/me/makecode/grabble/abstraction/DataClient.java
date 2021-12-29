package me.makecode.grabble.abstraction;

public class DataClient {
    private final Object dataClient;
    public DataClient(Object dataClient){
        this.dataClient = dataClient;
    }

    public Object getDataClient() {
        return dataClient;
    }
}