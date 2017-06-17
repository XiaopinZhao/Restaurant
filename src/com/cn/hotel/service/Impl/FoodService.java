package com.cn.hotel.service.Impl;

import java.util.List;

import com.cn.hotel.dao.IFoodDao;
import com.cn.hotel.dao.Impl.FoodDao;
import com.cn.hotel.entity.Food;
import com.cn.hotel.service.IFoodService;
import com.cn.hotel.utils.PageBean;

public class FoodService implements IFoodService{
	private IFoodDao dao=new FoodDao();
	/*
	 * 得到所有的食物信息
	 */
	public List<Food> getFoodList() {
		List<Food> foodList = dao.getFoodList();
		return foodList;
	}
	
	/*
	 * 添加一条食物信息
	 */
	public void addFood(Food food) {
		dao.addFood(food);
	}
	
	/*
	 * 删除一条食物信息
	 */
	public void deleteFood(int id) {
		dao.deleteFood(id);
	}
	
	/*
	 * 更新一条食物信息
	 */
	public void updateFood(Food food) {
		dao.updateFood(food);
	}
	
	/*
	 * 根据ID得到一条食物信息
	 */
	public Food findFoodById(int id) {
		
		return dao.findFoodById(id);
	}
	
	/*
	 * 根据pageBean的条件查找食物信息
	 */
	public void getAll(PageBean pb) {
		dao.getAll(pb);
	}

}
