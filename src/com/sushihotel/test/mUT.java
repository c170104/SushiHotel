package com.sushihotel.test;

import com.sushihotel.menu.Meal;
import com.sushihotel.menu.MenuMgr;

public class mUT {
	public static void main(String[] args) {
		Meal m1 = new Meal("aaa", "Aaa", "aaa", (float)10.0);
		Meal m2 = new Meal("bBb", "bBb", "bBb", (float)10.0);
		Meal m3 = new Meal("ccC", "ccC", "ccC", (float)10.0);
		MenuMgr mgr = new MenuMgr();
		mgr.addNewMeal(m1);
		mgr.addNewMeal(m2);
		mgr.addNewMeal(m3);
	}

}
