package com.cn.hotel.service;

import java.util.List;

import com.cn.hotel.entity.FoodType;

public interface IFoodTypeService {
	/*
	 * 得到所有的菜类别信息
	 */
	public List<FoodType> getFoodTypeList();
	/*
	 * 添加一条菜类别信息
	 */
	public void addFoodType(FoodType foodType);
	/*
	 * 更新一条菜类别信息
	 */
	public void updateFoodType(FoodType foodType);
	/*
	 * 根据ID得到一条材类别信息
	 */
	public FoodType findFoodTypeById(int id);
	/*
	 * 删除一条菜类别信息
	 */
	public void deleteFoodType(int id);
}	
