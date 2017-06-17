package com.cn.hotel.servlet;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.hotel.entity.DinnerTable;
import com.cn.hotel.entity.Food;
import com.cn.hotel.entity.FoodType;
import com.cn.hotel.entity.Orders;
import com.cn.hotel.utils.Condition;
import com.cn.hotel.utils.PageBean;
/**
 * 主页面访问的类
 */
public class IndexServlet extends BaseServlet {

	/*
	 * 获得当前页的所有的菜信息
	 */
	public Object getMenu(HttpServletRequest request,HttpServletResponse response){
		Object uri=null;
		HttpSession session=request.getSession();
		//获取session里边的值
		Object obj = session.getAttribute("table_id");
		
		String table_id=request.getParameter("table_id");
		if(table_id!=null){
			tableService.chageStatus(Integer.parseInt(table_id));
			session.setAttribute("table_id", table_id);
			
		}else{
			table_id=(String)session.getAttribute("table_id");
		}
		
		
		//查询菜单系统
		List<FoodType> foodTypeList = typeService.getFoodTypeList();
		request.setAttribute("foodTypeList", foodTypeList);
		
		//获取菜单页面信息
		PageBean<Food> pb=new PageBean<Food>();
		Condition condition=new Condition();
		
		//获取页面得到的参数
		String foodtype=request.getParameter("foodtype");
		String foodName=request.getParameter("foodName");
		if(foodtype!=null&&!"".equals(foodtype.trim())){
			condition.setFoodType_id(Integer.parseInt(foodtype));
			pb.setCondition(condition);
			request.setAttribute("foodtypeId", foodtype);
		}
		if(foodName!=null&&!"".equals(foodName.trim())){
			condition.setFoodName(foodName);
			pb.setCondition(condition);
		}
		
		pb.setPageCount(6);
		String curPage=request.getParameter("currentPage");
		if(curPage==null||curPage.isEmpty()){
			pb.setCurrentPage(1);
		}
		if(curPage!=null&&!curPage.isEmpty()){
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		
		foodService.getAll(pb);
		
		request.setAttribute("pageBean", pb);
		return "/app/detail/caidan.jsp";
	}

	/*
	 * 得到选中行的菜的详细信息
	 */
	public Object getDetailCai(HttpServletRequest request,HttpServletResponse response){
		//得到所有的菜类别并封装到list集合中
		List<FoodType> foodList = typeService.getFoodTypeList();
		request.setAttribute("foodTypes", foodList);
		String food_id=request.getParameter("food_id");
		if(food_id!=null&&!"".equals(food_id.trim())){
			Food food = foodService.findFoodById(Integer.parseInt(food_id));
			request.setAttribute("food", food);
		}
		return "/app/detail/caixiangxi.jsp";
	}

	
}
