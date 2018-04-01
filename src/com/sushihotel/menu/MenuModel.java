package com.sushihotel.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.menu.Meal;;


public class MenuModel {
	private static IDataStore dataStore = DataStoreFactory.getDataStore();
	

	private static final String EmptyDBMsg = "Menu DB not found.";
	
	protected static boolean create(Meal Meal) throws DuplicateData{
		List list;
		int size;
		Meal dbMeal;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.MENU);
		
		size = list == null ? 0 : list.size(); // size assigned to 0 if list == null else size == list.size()
		
		if(list == null)    {
            list = new ArrayList(); // declare array list without specific <obj> ref
        }    
		
// 		Checks Meal against dbMeal(reflects list) for existing meal based on mealID, mealName
        for(int i=0; i<size; i++)   {
            dbMeal = (Meal)list.get(i);
//            if(dbMeal.getMealID() == Meal.getMealID()) {
//            	throw new DuplicateData(""+Meal.getMealID(), MENU_SEARCH_TYPE.MEAL_ID); // DuplicateData(String duplicateData, Enum type)
//            }
            if(dbMeal.getMealName().toLowerCase().equals(Meal.getMealName().toLowerCase())) {
            	throw new DuplicateData(Meal.getMealName(), Meal.MENU_SEARCH_TYPE);
            }
        }
        
// 		mealID automatically set on creation
        Meal.setMealID(size + 1); // 
        list.add(Meal);
        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.MENU);
	
	}
        
        
    protected static Meal read(String mealName) throws EmptyDB, InvalidEntity {
    	
    	List list;
    	Meal meal;
    	
    	list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.MENU);
    	if(list == null)
            new EmptyDB(EmptyDBMsg);
    	
        for (int i=0; i<list.size(); i++) {
        	meal =(Meal)list.get(i);
        	
        	if (meal.getMealName().toLowerCase().equals((mealName.toLowerCase())));
        	return meal;
        }
    	
        throw new InvalidEntity(mealName + " not found.", MENU_SEARCH_TYPE.MEAL_NAME);
    }
        
    protected static List<Meal> read() throws EmptyDB{ 
    	List list = null;
        List<Meal> newList = new ArrayList();
        Meal meal;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.MENU);
        
        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        for(int i=0; i<list.size(); i++)    {
            meal = (Meal)list.get(i);
                newList.add(meal);
        }
        return newList;
    	
    }
    
	protected static boolean update(String mealName, Meal meal) throws EmptyDB, InvalidEntity{ 
		List list;
        Iterator iter; // iterator used to traverse through list
        Meal dbMeal;
        boolean trigger_flag = false;
		
        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.MENU); //assign Menu data from database into a List, (can use either arraylist/list)??
		
        if (list == null) { // empty list, throw exception EmptyDB
        	throw new EmptyDB(EmptyDBMsg);
        }
        
        iter = list.iterator(); // returns an object of type iterator, points above the first MealName, must use .next();
        while(iter.hasNext())   { //check if there is next item in list
            dbMeal = (Meal)iter.next(); // assign next item
            if(dbMeal.getMealName().toLowerCase().equals((mealName.toLowerCase()))) { // remove if mealname is found
                iter.remove(); // removes 
                trigger_flag = true;
                break;
            }
        }
        if(!trigger_flag) 
            throw new InvalidEntity(mealName + " not found. ", MENU_SEARCH_TYPE.MEAL_NAME);
        
        list.add(meal); 
        
        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.MENU);
	}
	
	protected static boolean delete(int mealID) throws EmptyDB, InvalidEntity {
		List list;
		Iterator iter;
		Meal dbMeal;
		boolean trigger_flag = false;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.MENU);
		
		if(list == null) {
			throw new EmptyDB(EmptyDBMsg);
		}
		
		iter = list.iterator();
		while(iter.hasNext()) {
			dbMeal = (Meal)iter.next();
			if (dbMeal.getMealID() == mealID) {
				iter.remove();
				trigger_flag = true;
				break;
			}
		}
		if (!trigger_flag) {
			throw new InvalidEntity(mealID + " not found.", MENU_SEARCH_TYPE.MEAL_ID);
		}
		
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.MENU);
		
	}
}
