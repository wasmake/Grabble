package me.makecode.grabble.abstraction;

public class GenericDatastorage {
    private Object dataSource;
    public GenericDatastorage(Object dataSource){
        this.dataSource = dataSource;
    }

    public Object getDataSource(){
        return dataSource;
    }
}
