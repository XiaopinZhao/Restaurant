package com.cn.hotel.dao;

import java.util.List;

import com.cn.hotel.entity.Food;
import com.cn.hotel.utils.PageBean;
/**
 * 对food表进行操做的接口
 */
public interface IFoodDao {
	/*
	 * 得到所有的食物信息
	 */
	public List<Food> getFoodList();
	/*
	 * 根据id找到食物信息
	 */
	public Food findFoodById(int id);
	/*
	 * 更新食物信息
	 */
	public void updateFood(Food food);
	/*
	 * 根据食物ID删除食物信息
	 */
	public void deleteFood(int id);
	/*
	 * 增加一条食物信息
	 */
	public void addFood(Food food);
	/*
	 * 得到一共有多少页数据
	 */
	public int getTotalCount(PageBean<Food> pb);
	
	/*
	 * 查找所有符合条件的信息并放到pagedate属性中
	 */
	public void getAll(PageBean<Food> pb);
}
