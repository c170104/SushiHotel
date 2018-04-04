package com.sushihotel.test;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;

public class dbUT   {

    public static void main(String[] args)  {
        configFileInputTest();
    }

    public static void configFileInputTest()    {
        IDataStore ds = DataStoreFactory.getDataStore();

        System.out.println(ds.getDataStoreType());
    }
}