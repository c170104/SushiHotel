package com.sushihotel.database;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import com.sushihotel.tools.ReadPropValues;

public class FlatFileIO implements IDataStore {

    public String getDataStoreType() {
        return ReadPropValues.CONFIG_FILE_PROPERTY_DB_TYPE_FILE;
    }


    public List read(Enum entityType) {
        try {
            String fileName = "";
            if(entityType == DB_ENTITY_TYPE.GUEST)
                fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_GUEST;
            else if(entityType == DB_ENTITY_TYPE.ROOM)
                fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_ROOM;
            else if(entityType == DB_ENTITY_TYPE.INVOICE)
                fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_INVOICE;
            else if(entityType == DB_ENTITY_TYPE.MENU)
                fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_MENU;
            else if(entityType == DB_ENTITY_TYPE.ROOMSERVICE)
                fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_ROOMSERVICE;
            else if(entityType == DB_ENTITY_TYPE.RESERVATION)
                fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_RESERVATION;

            return readSerializedObject(fileName);
        } catch(FileNotFoundException fnfe) {
            System.out.println("Database file type" + entityType + " not found.");
            System.out.println("Creating Database file type" + entityType + ".");
            return null;
        }
    }

    public boolean write(List content, Enum entityType) {
        String fileName = "";
        if (entityType == DB_ENTITY_TYPE.GUEST)
            fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_GUEST;
        else if (entityType == DB_ENTITY_TYPE.ROOM)
            fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_ROOM;
        else if (entityType == DB_ENTITY_TYPE.INVOICE)
            fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_INVOICE;
        else if (entityType == DB_ENTITY_TYPE.MENU)
            fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_MENU;
        else if (entityType == DB_ENTITY_TYPE.ROOMSERVICE)
            fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_ROOMSERVICE;
        else if (entityType == DB_ENTITY_TYPE.RESERVATION)
            fileName = ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_RESERVATION;

        return writeSerializedObject(fileName, content);
    }

    public List readSerializedObject(String fileName) throws FileNotFoundException {
        List fileContent = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        
        try {
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            fileContent = (ArrayList) in.readObject();
            in.close();  
        } catch(IOException ioe)  {
            System.out.println("IOException: " + ioe.getMessage());
        } catch(ClassNotFoundException cnfe)  {
            System.out.println("Class Exception: " + cnfe.getMessage());
        }

        return fileContent;
    }

    public boolean writeSerializedObject(String fileName, List content)  {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(content);
            oos.close();
        } catch(IOException ioe)  {
            System.out.println("IOException: " + ioe.getMessage());
            ioe.printStackTrace();
            return false;
        }
        return true;
    }
}