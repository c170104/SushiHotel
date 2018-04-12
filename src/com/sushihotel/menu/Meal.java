package com.sushihotel.menu;

import java.io.Serializable;

public class Meal implements Serializable{
	private int mealID;
	private String mealName;
	private String description;
	private String preparedMethod;
	private float mealPrice;
	
	public enum MENU_SEARCH_TYPE {
        MEAL_ID,
        MEAL_NAME
    }
	
	public Meal (
			String mealName,
			String description,
			String preparedMethod,
			float mealPrice) {
		this.mealName = mealName;
		this.description = description;
		this.preparedMethod = preparedMethod;
		this.mealPrice = mealPrice;
	}
	
	protected void setMealID(int mealID) {
		this.mealID = mealID;
	}
	protected void setMealName(String mealName) {
		this.mealName = mealName;
	}
	protected void setDesc(String description) {
		this.description = description;
	}
	protected void setPreparedMethod(String preparedMethod) {
		this.preparedMethod = preparedMethod;
	}
	protected void setMealPrice(float mealPrice) {
		this.mealPrice = mealPrice;
	}
	
	public int getMealID() {
		return this.mealID;
	}
	public String getMealName() {
		return this.mealName;
	}
	public String getDesc() {
		return this.description;
	}
	public String getPreparedMethod() {
		return this.preparedMethod;
	}
	public float getMealPrice() {
		return this.mealPrice;
	}
	
}
