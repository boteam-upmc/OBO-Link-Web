package com.exception;

public class EntityException extends Exception{
    public EntityException() {
        super();
    }

    public EntityException(String login){
        super(login+ " not found");
    }

    public EntityException(int id){
        super(id+ " not found");
    }


}
