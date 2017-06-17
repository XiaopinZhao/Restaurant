package com.cn.hotel.utils;
/**
 * pageBean的辅助对象
 */
public class Condition {
	private int foodType_id;      //菜类别ID
	private String FoodName;	//菜的名字
	public int getFoodType_id() {
		return foodType_id;
	}
	public void setFoodType_id(int foodType_id) {
		this.foodType_id = foodType_id;
	}
	public String getFoodName() {
		return FoodName;
	}
	public void setFoodName(String foodName) {
		FoodName = foodName;
	}
	

	
}
