package com.sushihotel.menu;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class MenuMgr {
	private static final Logger logger = Logger.getLogger(MenuMgr.class.getName()); // gets name of the method
	 
	public boolean addNewMeal(Meal meal) {
		try {
			if (MenuModel.create(meal)) {
				logger.info("[CREATE SUCCESS] Meal ID: " + Integer.toString(meal.getMealID()) + " | Meal Name: " + meal.getMealName());
                return true;
			} else {
				logger.info("[CREATE FAIL] Meal ID: " + Integer.toString(meal.getMealID()));
			}
		} catch (DuplicateData dd) {
			logger.log(Level.WARNING, dd.getMessage());
		}
		return false;
		 
	}
	 
	public boolean removeMeal(int mealID) {
		 try {
			if (MenuModel.delete(mealID)) {
				logger.info("[DELETE SUCCESS] Meal ID: " + Integer.toString(mealID));
				return true;
			} else {
				logger.info("[DELETE FAIL] Meal ID: " + Integer.toString(mealID));
			}
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	}
	 
	public boolean editMeal(int mealID, Meal meal) {
		try {
			if(MenuModel.update(mealID, meal)) {
				logger.info("[UPDATE SUCCESS] Meal ID: " + Integer.toString(mealID) + " | Meal Name: " + meal.getMealName());
				return true;
			} else {
				logger.info("[UPDATE FAIL] Meal ID: " + Integer.toString(meal.getMealID()));
			}
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	}
	 
	public Meal getMealDetails(int mealID) {
		Meal meal = null;
		try {
			meal = MenuModel.read(mealID);
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}		 
		return meal;		 
	}
	 
	public List<Meal> getMealOffered() { 
		 List<Meal> mealList = null;
		 try {
			mealList = MenuModel.read();
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		}
		 return mealList;
	}
}
