package com.sushihotel.exception;

public class InvalidEntity extends Exception {
    String msg;
    Enum entity;

    public InvalidEntity(String msg, Enum entity)    {
        this.msg = msg;
        this.entity = entity;
    }

    public String getMsg()  {
        return this.msg;
    }

    public Enum getEntity() {
        return this.entity;
    }

    public String getMessage()  {
        return "(" + getEntity() + ") " + getMsg();
    }
}