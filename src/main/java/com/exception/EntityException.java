package com.exception;

public class EntityException extends Exception{
    public EntityException() {
        super();
    }

    public EntityException(int id){
        super(id+ " not found");
    }


}
