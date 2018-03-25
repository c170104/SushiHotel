package com.sushihotel.database;

import com.sushihotel.tools.ReadPropValues;

public class FlatFileIO implements IDataStore {
    public String getDataStoreTString() {
        return ReadPropValues.DB_TYPE.FILE;
    }

    
}