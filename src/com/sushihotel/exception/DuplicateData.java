package com.sushihotel.exception;

import com.sushihotel.guest.GuestModel;

public class DuplicateData extends Exception {
    private String duplicateData;
    private Enum type;

    public DuplicateData(String duplicateData, Enum type ) {
        this.duplicateData = duplicateData;
        this.type = type;
    }

    public String getDuplicateData()    {
        return this.duplicateData;
    }

    public Enum getType() {
        return this.type;
    }

    public String getMessage()  {
        return "Duplicated Data of type (" + getType() + ") found: " + getDuplicateData() + ".";
    }
}