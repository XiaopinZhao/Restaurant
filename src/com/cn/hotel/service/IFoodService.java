package com.cn.hotel.service;

import java.util.List;

import com.cn.hotel.entity.Food;
import com.cn.hotel.utils.PageBean;

public interface IFoodService {
	/*
	 * 得到所有的食物信息
	 */
	public List<Food> getFoodList();
	/*
	 * 添加一条食物信息
	 */
	public void addFood(Food food);
	/*
	 * 删除一条食物信息
	 */
	public void deleteFood(int id);
	/*
	 * 更新一条食物信息
	 */
	public void updateFood(Food food);
	/*
	 * 根据ID得到一条食物信息
	 */
	public Food findFoodById(int id);
	/*
	 * 根据pageBean的条件查找食物信息
	 */
	public void getAll(PageBean pb);
}
