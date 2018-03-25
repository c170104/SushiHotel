package com.sushihotel.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropValues {
    String[] result;
    InputStream inputStream;
    constant CONFIG_FILE_NAME = "config.properties";
    constant CONFIG_FILE_PROPERTY_DB_TYPE = "databaseType";

    public String[] getConfigPropValues() throws IOException  {
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
            result.add(prop.getProperty(CONFIG_FILE_PROPERTY_DB_TYPE));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            inputStream.close();
        }
        
        return result;
    }
}