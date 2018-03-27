package com.sushihotel.database;

import java.io.FileNotFoundException;
import java.util.List;

public interface IDataStore {
    public String getDataStoreType();
    public boolean tableExist();
    
    public List readGuest();
    public boolean writeGuest(List l);

}