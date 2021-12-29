package me.makecode.grabble.handler;

public class DataInvalidIdentifierException extends RuntimeException {

    private String msg;
    public DataInvalidIdentifierException(String msg){
        super("Invalid Identifier when trying to get data " + msg);
        this.msg = "Invalid Identifier when trying to get data " + msg;
    }

    public String getMsg() {
        return msg;
    }
}

