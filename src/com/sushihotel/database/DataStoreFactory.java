package com.sushihotel.database;

import java.io.IOException;
import java.util.Properties;
import com.sushihotel.tools.ReadPropValues;

public class DataStoreFactory  {
    public static IDataStore getDataStore() {
        Properties configProperties;
        ReadPropValues readPropValues = new ReadPropValues();
        String configDatabaseType;
        IDataStore dataStore = null;   // Default

        try {
            configProperties = readPropValues.getConfigPropValues();
            configDatabaseType = configProperties.getProperty(ReadPropValues.CONFIG_FILE_PROPERTY_DB_TYPE);
            
            if(configDatabaseType.equals(ReadPropValues.CONFIG_FILE_PROPERTY_DB_TYPE_FILE))   {
                dataStore = new FlatFileIO();  
            }
            
        } catch(IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
            System.exit(1);
        }
        return dataStore;   
    }
}