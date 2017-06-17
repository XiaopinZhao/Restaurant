package com.cn.hotel.dao;

import java.util.List;

import com.cn.hotel.entity.FoodType;
/**
 * 对菜类别进行操作的接口
 */
public interface IFoodTypeDao {
	/*
	 * 得到所有的食物类别信息
	 */
	public List<FoodType> getFoodTypeList();
	/*
	 * 添加一条食物类别信息
	 */
	public void addFoodTypeList(FoodType foodType);
	/*
	 * 根据菜类别判断是否存在
	 */
	public boolean isExist(String typeName);
	/*
	 * 更新菜类别对象
	 */
	public void updataFoodType(FoodType foodType);
	/*
	 * 根据id得到材类别对象
	 */
	public FoodType findFoodTypeById(int id);
	/*
	 * 删除菜类别对象
	 */
	public void deleteFoodType(int id);
}
