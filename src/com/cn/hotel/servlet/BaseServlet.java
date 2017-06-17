package com.cn.hotel.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hotel.factory.BeanFactory;
import com.cn.hotel.service.Impl.DinnerTableService;
import com.cn.hotel.service.Impl.FoodService;
import com.cn.hotel.service.Impl.FoodTypeService;
import com.cn.hotel.service.Impl.OrderDetailService;
import com.cn.hotel.service.Impl.OrdersService;
import com.cn.hotel.utils.WebUtils;
/**
 * 所有servlet的超类
 */
public class BaseServlet extends HttpServlet {
	
	protected DinnerTableService tableService=BeanFactory.getInstance("DinnerTableService", DinnerTableService.class);
	protected FoodService foodService=BeanFactory.getInstance("FoodService", FoodService.class);
	protected FoodTypeService typeService=BeanFactory.getInstance("FoodTypeService", FoodTypeService.class);
	protected OrdersService ordersService=BeanFactory.getInstance("OrdersService", OrdersService.class);
	protected OrderDetailService detailService=BeanFactory.getInstance("OrderDetailService", OrderDetailService.class);
	
	/*
	 *get方法 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求编码
		request.setCharacterEncoding("utf-8");
		//得到页面要请求的Servlet的方法
		String name=request.getParameter("method");
		//得到当前类的运行时文件
		Class clazz = this.getClass();
		Object returnValue=null;
		try {
			//得到运行类文件的一个方法
			Method method = clazz.getMethod(name, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法并返回结果
			returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//通过webUtils进行路径跳转
		WebUtils.gotoUri(request, response, (String)returnValue);

	}

	/*
	 * post方法
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用doGet方法
		doGet(request,response);
	}

}
