package com.cn.hotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hotel.entity.DinnerTable;
import com.cn.hotel.factory.BeanFactory;
import com.cn.hotel.service.IDinnerTableService;
import com.cn.hotel.service.Impl.DinnerTableService;
import com.cn.hotel.utils.WebUtils;
/**
 * 对餐桌进行操作的类
 */
public class BoardServlet extends BaseServlet {

	/*
	 * BoardServlet的初始化方法
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<DinnerTable> list = tableService.getDinnerTableList();
		config.getServletContext().setAttribute("table", list);
	}
	
	/*
	 * 得到餐桌的list集合并放到request域对象中
	 */
	public Object query(HttpServletRequest request, HttpServletResponse response){
		//得到所有的餐桌信息封装到list集合中
		List<DinnerTable> list = tableService.getDinnerTableList();
		if(list!=null){
			request.setAttribute("DinnerTableList", list);	
		}
		return "/sys/board/boardList.jsp";
	}
	
	/*
	 * 添加餐桌
	 */
	public Object addBoard(HttpServletRequest request,HttpServletResponse response){
		
		String tableName=request.getParameter("bName");
		System.out.println(tableName);
		DinnerTable table=new DinnerTable();
		table.setTableName(tableName);
		table.setTableStatus(0);
		tableService.addDinnerTable(table);
		return "/BoardServlet?method=query";
		
	}
	
	/*
	 * 删除一个餐桌
	 */
	public Object deleteDinnerTable(HttpServletRequest request,HttpServletResponse response){
		String idd=request.getParameter("id");
		int id=Integer.parseInt(idd);
		tableService.deleteDinnerTable(id);
		return "/BoardServlet?method=query";
		
	}
	/*
	 * 退桌
	 */
	public Object backTable(HttpServletRequest request,HttpServletResponse response){
		String idd=request.getParameter("id");
		int id=Integer.parseInt(idd);
		DinnerTable dinnerTable=new DinnerTable();
		dinnerTable.setId(id);
		dinnerTable.setTableStatus(0);
		tableService.backTable(dinnerTable);
		return "/BoardServlet?method=query";
	}

}
