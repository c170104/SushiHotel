package com.sushihotel.database;

import java.util.Properties;
import com.sushihotel.tools.ReadPropValues;

public class DataStoreFactory   {
    public IDataStore getDataStore()    {
        Properties configProperties;
        ReadPropValues readPropValues = new ReadPropValues();
        String configDatabaseType;
        IDataStore dataStore;

        configProperties = readPropValues.getConfigPropValues();
        configDatabaseType = configProperties.getProperty(ReadPropValues.CONFIG_FILE_PROPERTY_DB_TYPE);

        if(configDatabaseType == ReadPropValues.DB_TYPE.FILE)   {
            dataStore = new FlatFileIO();
            return dataStore;
        }
    }
}