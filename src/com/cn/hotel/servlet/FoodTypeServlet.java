package com.cn.hotel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hotel.entity.FoodType;
import com.cn.hotel.factory.BeanFactory;
import com.cn.hotel.service.IFoodTypeService;
import com.cn.hotel.service.Impl.FoodTypeService;
/**
 * 对菜类型操作的类
 */
public class FoodTypeServlet extends BaseServlet {

	/*
	 * 得到食物类型的集合
	 */
	public Object getFoodTypeList(HttpServletRequest request,HttpServletResponse response){
		List<FoodType> list = typeService.getFoodTypeList();
		if(list!=null){
			request.setAttribute("FoodTypeList", list);
		}
		return "/sys/foodType/cuisineList.jsp";
	}
	
	/*
	 * 添加食物类型
	 */
	public Object addFoodType(HttpServletRequest request,HttpServletResponse response){
		String typeName=request.getParameter("name");
		FoodType foodType=new FoodType();
		foodType.setTypeName(typeName);
		typeService.addFoodType(foodType);
		return "/FoodTypeServlet?method=getFoodTypeList";
	}

	/*
	 * 更新食物类型
	 */
	public Object updateFoodType(HttpServletRequest request,HttpServletResponse response){
		FoodType foodType=new FoodType(Integer.parseInt(request.getParameter("id")),request.getParameter("name"));
		typeService.updateFoodType(foodType);
		return "/FoodTypeServlet?method=getFoodTypeList";
	}

	/*
	 * 修改食物类型
	 */
	public Object updataFoodType(HttpServletRequest request,HttpServletResponse response){
		String idd=request.getParameter("id");
		int id=Integer.parseInt(idd);
		FoodType foodType = typeService.findFoodTypeById(id);
		request.setAttribute("foodType", foodType);
		return "/sys/foodType/updateCuisine.jsp";
	}
	
	/*
	 * 删除食物类型
	 */
	public Object deleteFoodType(HttpServletRequest request, HttpServletResponse response){
		String idd=request.getParameter("id");
		int id=Integer.parseInt(idd);
		typeService.deleteFoodType(id);
		return "/FoodTypeServlet?method=getFoodTypeList";
	}


}
