package com.sushihotel.database;

import java.io.FileNotFoundException;
import java.util.List;

public interface IDataStore {
    public enum DB_ENTITY_TYPE  {
        GUEST,
        ROOM,
        RESERVATION,
        INVOICE,
        ROOMSERVICE,
        MENU
    }

    public String getDataStoreType();
    public List read(Enum dbEntityType);
    public boolean write(List l, Enum dbEntityType);

}