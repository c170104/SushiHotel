package com.sushihotel.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropValues {
    public static constant CONFIG_FILE_NAME = "config.properties";
    public static constant CONFIG_FILE_PROPERTY_DB_TYPE = "databaseType";
    public static enum DB_TYPE {
        FILE,
        MYSQL,
        ACCESS
    }

    String[] result;
    InputStream inputStream;

    public Properties getConfigPropValues() throws IOException  {
        try {
            Properties prop = new Properties();
            String configPropFileName = CONFIG_FILE_NAME;

            inputStream = getClass().getClassLoader().getResourceAsStream(configPropFileName);

            if(inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file: " + configPropFileName + " not found.");
            }

            // ADD ALL THE DIFFERENT PROPERTIES INTO THE LIST
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            inputStream.close();
        }
        
        return prop;
    }
}