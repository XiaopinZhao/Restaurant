package com.cn.hotel.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cn.hotel.entity.Food;
import com.cn.hotel.entity.FoodType;
import com.cn.hotel.factory.BeanFactory;
import com.cn.hotel.service.IFoodService;
import com.cn.hotel.service.IFoodTypeService;
import com.cn.hotel.service.Impl.FoodService;
import com.cn.hotel.service.Impl.FoodTypeService;

/**
 * 对食物进行操作的类
 */
public class FoodServlet extends BaseServlet {
	
	/*
	 *为修改数据做准备
	 */
	public Object preUpdate(HttpServletRequest request,HttpServletResponse response){
		List<FoodType> foodTypeList = typeService.getFoodTypeList();
		request.setAttribute("foodTypeList", foodTypeList);
		String idd=request.getParameter("id");
		int id=Integer.parseInt(idd);
		Food food = foodService.findFoodById(id);
		request.setAttribute("food", food);
		return "/sys/food/updateFood.jsp";
	}
	
	/*
	 * 为添加数据做准备
	 */
	public Object preAdd(HttpServletRequest request,HttpServletResponse response){
		List<FoodType> foodTypeList = typeService.getFoodTypeList();
		request.setAttribute("foodTypeList", foodTypeList);
		return "/sys/food/saveFood.jsp";
	}
	
	/*
	 * 得到所有的list
	 */
	public Object getList(HttpServletRequest request,HttpServletResponse response){
		List<Food> foodList = foodService.getFoodList();
		List<FoodType> foodTypeList = typeService.getFoodTypeList();
		request.setAttribute("foodList", foodList);
		request.setAttribute("types", foodTypeList);
		return "/sys/food/foodList.jsp";
	}
	
	/*
	 * 添加食物记录
	 */
	public Object saveFood(HttpServletRequest request,HttpServletResponse response){
		try{
			Food food=new Food();
			//创建文件上传工厂类
			FileItemFactory factory=new DiskFileItemFactory();
			//文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置文件上传大小参数设置
			upload.setFileSizeMax(10*1024*1024);
			upload.setSizeMax(50*1024*1024);
			upload.setHeaderEncoding("utf-8");
			
			//判断是不是包含多个请求
			if(upload.isMultipartContent(request)){
				List<FileItem> parseRequest = upload.parseRequest(request);
				for (FileItem item : parseRequest) {
					//判断是否是普通文本数据
					if(item.isFormField()){
						String name=item.getFieldName();
						String value=item.getString();
						value=new String(value.getBytes("ISO8859-1"),"utf-8");
						System.out.println(name+"----->"+value);
						String property = System.getProperty("java.classpath");
						BeanUtils.copyProperty(food, name, value);
						
					}else{
						/*****文件上传*****/
						//得到文件上传的名称
						String name=item.getName();
						System.out.println("image name is --->"+name);
						//随机获取一个UUID
						String id=UUID.randomUUID().toString();
						//上传的文件名称
						name=id+"_"+name;
						//上传到的路径
						String basePath=getServletContext().getRealPath("/upload");
						System.out.println("basepath is--->"+basePath);
						food.setImg("/upload/"+name);
						File file=new File(basePath,name);
						item.write(file);
						item.delete();
						foodService.addFood(food);
					
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/FoodServlet?method=getList";
	}
	
	/**
	 * 修改食物信息
	 */
	public Object updateFood(HttpServletRequest request,HttpServletResponse response){
		try{
			int id=Integer.parseInt(request.getParameter("id"));
			Food food=foodService.findFoodById(id);
			System.out.println("food before food-->"+food);
			//创建工厂
			FileItemFactory factory=new DiskFileItemFactory();
			//创建文件上传核心类
			ServletFileUpload upload = new ServletFileUpload(factory);
			if(upload.isMultipartContent(request)){
				List<FileItem> parseRequest = upload.parseRequest(request);
				for (FileItem item : parseRequest) {
					if(item.isFormField()){
						String name=item.getFieldName();
						String value=item.getString();
						value=new String(value.getBytes("ISO8859-1"),"utf-8");
						if(!"".equals(value)&&value!=null){
							System.out.println(name+"--->"+value);
							BeanUtils.copyProperty(food, name, value);
						}else{
							
						}
					}else{
						String na=item.getString();
						System.out.println("na--->"+na);
						if(na!=null&&!"".equals(na)){
							//图片发生了更改，重新进行上传。
							/*****文件上传*****/
							//得到文件上传的名称
							String name=item.getName();
							System.out.println("image name is --->"+name);
							//随机获取一个UUID
							String idd=UUID.randomUUID().toString();
							//上传的文件名称
							name=idd+"_"+name;
							//上传到的路径
							String basePath=getServletContext().getRealPath("/upload");
							System.out.println("basepath is--->"+basePath);
							food.setImg("/upload/"+name);
							File file=new File(basePath,name);
							item.write(file);
							item.delete();
							
						}
						System.out.println("food is-->:"+food);
						foodService.updateFood(food);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/FoodServlet?method=getList";
	}

	/*
	 * 删除食物
	 */
	public Object deleteFood(HttpServletRequest request,HttpServletResponse response){
		String idd=request.getParameter("id");
		int id=Integer.parseInt(idd);
		foodService.deleteFood(id);
		return "/FoodServlet?method=getList";
	}

}
