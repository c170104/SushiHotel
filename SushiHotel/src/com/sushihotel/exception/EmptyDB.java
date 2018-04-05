package com.sushihotel.exception;

public class EmptyDB extends Exception  {
    String msg;

    public EmptyDB(String msg)  {
        this.msg = msg;
    }

    public String getMessage()  {
        return this.msg;
    }
}