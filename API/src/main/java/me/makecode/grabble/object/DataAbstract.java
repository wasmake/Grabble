package me.makecode.grabble.object;

import java.util.Date;

public abstract class DataAbstract {

    private Date createdAt, updatedAt, lastLoadedAt;
    private transient  Class<?> clazz;

    public DataAbstract(Class<?> clazz){
        this.createdAt = new Date();
        this.clazz = clazz;
    }

    public void onLoad(){
        this.lastLoadedAt = new Date();
    }

    public void onSave(){
        this.updatedAt = new Date();
    }


}
