package com.sushihotel.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropValues {
    public static final String CONFIG_FILE_NAME = "resources/config.properties";
    public static final String CONFIG_FILE_PROPERTY_DB_TYPE = "databaseType";
    public static final String CONFIG_FILE_PROPERTY_DB_TYPE_FILE = "FILE";
    public static final String CONFIG_FILE_PROPERTY_DB_TYPE_MYSQL = "MYSQL";
    public static final String CONFIG_FILE_PROPERTY_DB_FILE_NAME_GUEST = "resources/db/guest.dat";
    public static final String CONFIG_FILE_PROPERTY_DB_FILE_NAME_ROOM = "resources/db/room.dat";
    public static final String CONFIG_FILE_PROPERTY_DB_FILE_NAME_INVOICE = "resources/db/invoice.dat";
    public static final String CONFIG_FILE_PROPERTY_DB_FILE_NAME_ROOMSERVICE = "resources/db/roomservice.dat";
    public static final String CONFIG_FILE_PROPERTY_DB_FILE_NAME_RESERVATION = "resources/db/reservation.dat";
    public static final String CONFIG_FILE_PROPERTY_DB_FILE_NAME_MENU = "resources/db/menu.dat";

    public Properties getConfigPropValues() throws IOException  {
        InputStream inputStream;
        Properties prop = new Properties();
        
        try {
            inputStream = new FileInputStream(CONFIG_FILE_NAME);
            
            if(inputStream != null)
                prop.load(inputStream);

        } catch (FileNotFoundException e) {
            System.out.println("File Exception: " + e.getMessage());
        }
        
        return prop;
    }
}