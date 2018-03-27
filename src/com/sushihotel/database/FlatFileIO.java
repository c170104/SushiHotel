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
    public static final String FILE_DB_DELIMETER = "|";

    public String getDataStoreType() {
        return ReadPropValues.CONFIG_FILE_PROPERTY_DB_TYPE_FILE;
    }

    public List readGuest() {
        try {
            return readSerializedObject(ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_GUEST);
        } catch(FileNotFoundException fnfe) {
            System.out.println("Database file " + ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_GUEST + " not found.");
            System.out.println("Creating Database file " + ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_GUEST + ".");
            return null;
        }
    }

    public boolean writeGuest(List content) {
        return writeSerializedObject(ReadPropValues.CONFIG_FILE_PROPERTY_DB_FILE_NAME_GUEST, content);
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