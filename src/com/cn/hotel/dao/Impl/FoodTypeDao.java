package com.cn.hotel.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.hotel.dao.IFoodTypeDao;
import com.cn.hotel.entity.FoodType;
import com.cn.hotel.utils.JdbcUtil;
/**
 * 对菜类别进行操作的实现类
 */
public class FoodTypeDao implements IFoodTypeDao {
	private QueryRunner qr=JdbcUtil.getQuery();
	
	/*
	 * 得到所有的食物类别信息
	 */
	public List<FoodType> getFoodTypeList() {
		String sql="select * from foodtype";
		List<FoodType> list=null;
		try{
			list = qr.query(sql, new BeanListHandler<FoodType>(FoodType.class));
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 添加一条食物类别信息
	 */
	public void addFoodTypeList(FoodType foodType) {
		String sql="insert into foodtype (typeName) values (?)";
		try{
			qr.update(sql,foodType.getTypeName());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 根据菜类别判断是否存在
	 */
	public boolean isExist(String typeName) {
		String sql="select * from foodtype where typeName=?";
		FoodType query=null;
		boolean a=false;
		try{
			 query= qr.query(sql, new BeanHandler<FoodType>(FoodType.class),typeName);
			if(query==null){
				a=false;
			}else{
				a=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}

	/*
	 * 更新菜类别对象
	 */
	public void updataFoodType(FoodType foodType) {
		String sql="UPDATE foodtype SET typeName=? WHERE id=?";
		try{
			qr.update(sql,foodType.getTypeName(),foodType.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 根据id得到材类别对象
	 */
	public FoodType findFoodTypeById(int id) {
		String sql="select * from foodtype where id=?";
		FoodType foodType=null;
		try{
			foodType = qr.query(sql, new  BeanHandler<FoodType>(FoodType.class),id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return foodType;
	}

	/*
	 * 删除菜类别对象
	 */
	public void deleteFoodType(int id) {
		String sql="delete from foodtype where id=?";
		try{
			qr.update(sql,id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	


}
