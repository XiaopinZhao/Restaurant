package com.cn.hotel.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 页面跳转工具类
 * @author Administrator
 *
 */
public class WebUtils {
	/*
	 * 实现页面跳转
	 */
	public static  void gotoUri(HttpServletRequest request,HttpServletResponse response,String uri){
		try {
			request.getRequestDispatcher(uri).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
