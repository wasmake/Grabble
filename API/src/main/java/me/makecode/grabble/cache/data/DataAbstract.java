package me.makecode.grabble.cache.data;

import java.util.Date;

public abstract class DataAbstract {

    private String identifier;
    private Date createdAt, updatedAt, lastLoadedAt;
    private transient  Class<?> clazz;

    public DataAbstract(Class<?> clazz){
        this.createdAt = new Date();
        this.clazz = clazz;
    }

    public String getId() {
        return this.identifier;
    }

    public void onLoad(){
        this.lastLoadedAt = new Date();
    }

    public void onSave(){
        this.updatedAt = new Date();
    }

}
