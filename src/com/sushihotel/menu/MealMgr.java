package com.sushihotel.menu;

//import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import com.sushihotel.menu.Meal;
import com.sushihotel.menu.MenuModel;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class MealMgr {
	 private static final Logger logger = Logger.getLogger(MealMgr.class.getName()); // gets name of the method
	 
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
	 
	 public boolean removeMeal(String mealName) {
		 Meal meal;
		 int MealID;
		 try {
			meal = com.sushihotel.menu.MenuModel.read(mealName);
			if (meal==null) {
				return false;
			}
			MealID = meal.getMealID();
			if (MenuModel.delete(MealID)) {
				logger.info("[DELETE SUCCESS] Meal ID: " + Integer.toString(meal.getMealID()) + " | Meal Name: " + meal.getMealName());
				return true;
			} else {
				logger.info("[DELETE FAIL] Meal ID: " + Integer.toString(meal.getMealID()));
			}
			
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	 }
	 
	 public boolean editMeal(String mealName, Meal meal) {
		 
		int mealID;
		
		try {
			mealID = meal.getMealID();
			if(MenuModel.update(mealName, meal)) {
				logger.info("[UPDATE SUCCESS] Meal ID: " + Integer.toString(mealID) + " | Meal Name: " + meal.getMealName());
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
	 
	 public Meal showMealDetails(String mealName) {
		Meal meal = null;
		try {
			meal = MenuModel.read(mealName);
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}		 
		return meal;		 
	 }
	 
	 public List<Meal> showMealOffered() { 
		 List<Meal> mealList = null;
		 try {
			mealList = MenuModel.read();
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		}
		 return mealList;
	 }
}
