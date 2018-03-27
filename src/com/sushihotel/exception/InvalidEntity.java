package com.sushihotel.exception;

public class InvalidEntity extends Exception {
    String msg;
    String entity;

    public InvalidEntity(String msg, String entity)    {
        this.msg = msg;
        this.entity = entity;
    }

    public String getMessage()  {
        return "(" + entity + ") " + this.msg;
    }
}