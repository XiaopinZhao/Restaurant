package com.cn.hotel.service.Impl;

import java.util.List;

import com.cn.hotel.dao.IFoodTypeDao;
import com.cn.hotel.dao.Impl.FoodTypeDao;
import com.cn.hotel.entity.FoodType;
import com.cn.hotel.service.IFoodTypeService;

public class FoodTypeService implements IFoodTypeService {
	private IFoodTypeDao dao=new FoodTypeDao();
	
	/*
	 * 得到所有的菜类别信息
	 */
	public List<FoodType> getFoodTypeList() {
		List<FoodType> list=dao.getFoodTypeList();
		return list;
	}
	
	/*
	 * 添加一条菜类别信息
	 */
	public void addFoodType(FoodType foodType) {
		boolean exist = dao.isExist(foodType.getTypeName());
		if(exist==false){
			dao.addFoodTypeList(foodType);
		}
	}

	/*
	 * 更新一条菜类别信息
	 */
	public void updateFoodType(FoodType foodType) {
		boolean exist = dao.isExist(foodType.getTypeName());
		if(exist==false){
			dao.updataFoodType(foodType);
		}
	}

	/*
	 * 根据ID得到一条材类别信息
	 */
	public FoodType findFoodTypeById(int id) {
		
		return dao.findFoodTypeById(id);
	}

	/*
	 * 删除一条菜类别信息
	 */
	public void deleteFoodType(int id) {
			dao.deleteFoodType(id);
	}

}
