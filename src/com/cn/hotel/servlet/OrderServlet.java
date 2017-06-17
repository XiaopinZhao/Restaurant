package com.cn.hotel.servlet;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.hotel.entity.Food;
import com.cn.hotel.entity.FoodType;
import com.cn.hotel.entity.OrderDetail;
import com.cn.hotel.entity.Orders;
import com.cn.hotel.utils.PageBean;
/**
 * 处理订单请求的类
 */
public class OrderServlet extends BaseServlet {
	
	/*
	 * 下单
	 */
	public Object putDiningCar(HttpServletRequest request,HttpServletResponse response){
		boolean isExit=false;
		//得到food_id并将food_id存放到FOod对象中
		String food_id=request.getParameter("food_id");
		Food food=foodService.findFoodById(Integer.parseInt(food_id));
		
		//得到当前订单列表，并向其中加入一条订单
		Map<Food,Integer> map=new LinkedHashMap<Food,Integer>(); 
		HttpSession session=request.getSession();
		Map<Food,Integer> m=(Map<Food,Integer>) session.getAttribute("foods");
		if(m!=null){
		
			Set<Entry<Food, Integer>> entrySet = m.entrySet();
			for (Entry<Food, Integer> entry : entrySet) {
				if(entry.getKey().getId()==food.getId()){
					food=entry.getKey();
					isExit=true;
					break;
				}
			}
			if(isExit){
				Integer i=m.get(food);
				i=i+1;
				m.put(food, i);
			}else{
				m.put(food, 1);
			}
		}else{
			map.put(food,1);
		}
		if(m!=null){
			session.setAttribute("foods", m);
		}else{
			session.setAttribute("foods", map);
		}
		
		//将菜品放到request域对象中
		List<FoodType> foodTypeList = typeService.getFoodTypeList();
		request.setAttribute("foodTypeList", foodTypeList);
		return "/app/detail/clientCart.jsp";
	}
	
	/*
	 * 将订单列表的选中行删除
	 */
	public Object removeOrder(HttpServletRequest request,HttpServletResponse response){
		//得到session中的信息
		HttpSession session=request.getSession();
		Map<Food,Integer> map=(Map<Food,Integer>)session.getAttribute("foods");
		String food_id=request.getParameter("food_id");
		Food food=foodService.findFoodById(Integer.parseInt(food_id));
		Set<Entry<Food, Integer>> entrySet = map.entrySet();
		for (Entry<Food, Integer> entry : entrySet) {
			if(entry.getKey().getId()==food.getId()){
				food=entry.getKey();
				break;
			}
		}
		map.remove(food);
		return "/app/detail/clientCart.jsp";
	}
	
	
	/*
	 * 前端进行下单操作
	 */
	public Object setOrder(HttpServletRequest request,HttpServletResponse response){
		//得到所有的菜类别并放到request域对象中
		List<FoodType> foodTypeList = typeService.getFoodTypeList();
		request.setAttribute("foodTypeList", foodTypeList);
		
		//根据当前订单编号判断是否已经定过单
		int ordersId=0;
		HttpSession session=request.getSession();
		String table_id=(String)session.getAttribute("table_id");
		Orders orders=new Orders();
		orders.setTable_id(Integer.parseInt(table_id));
		orders.setOrderStatus(0);
		Orders exitOrders = ordersService.isExitOrders(orders);
		
		//如果没有定过单就向订单列表中添加一条数据并得到订单编号
		if(exitOrders==null){
			orders.setOrderDate(new Date());
			orders.setTotalPrice(0);
			ordersService.addOrders(orders);
			ordersId=ordersService.getMaxId();
		}else{
			//定过单就得到当前订单编号
			ordersId=exitOrders.getId();
		}
		//将订单编号放到session域对象中
		session.setAttribute("ordersId", ordersId);
		
		//判断下单的这种食物是否已经点过了
		String food_id=request.getParameter("food_id");
		int food_count=Integer.parseInt(request.getParameter("food_count"));
		Food food=foodService.findFoodById(Integer.parseInt(food_id));
		
		OrderDetail detail=new OrderDetail();
		detail.setOrderId(ordersId);
		detail.setFood_id(Integer.parseInt(food_id));
		OrderDetail isExitOrderDetail=detailService.getOrderDetailByOrderIdandFood_id(detail);//是否已经购买过这种东西
		detail.setFoodCount(food_count);
		//如果没有对这个食物下单就向orderdetail中添加一条数据
		if(isExitOrderDetail==null){
			detailService.addOrderDetail(detail);
		}else{
			//如果对这个食物下过单就将他的数量加上下单的数量
			int id= isExitOrderDetail.getId();
			detailService.updateOrderDetail(isExitOrderDetail,food_count);
		}
		ordersService.updateOrders(detail,ordersId);
		return "/OrderServlet?method=getMenuList";
	}
	
	/*
	 * 得到订单列表
	 */
	public Object getMenuList(HttpServletRequest request,HttpServletResponse response){
		//得到所有的菜类别并放到session对象中
		HttpSession session=request.getSession();
		String ordersIdd = String.valueOf(session.getAttribute("ordersId"));

		//得到所有的订单信息
		List<FoodType> foodTypeList = typeService.getFoodTypeList();
		request.setAttribute("foodTypeList", foodTypeList);
		double totalPrice=0;
		int ordersId=Integer.parseInt(ordersIdd);
		List<Map<String,Object>> ListMap =(List<Map<String,Object>>) ordersService.getAllMenuList(ordersId);
		for (Map<String, Object> map : ListMap) {
			String price = String.valueOf(map.get("price"));
			String foodCount = String.valueOf(map.get("foodCount"));
			totalPrice+=Double.parseDouble(foodCount)*Double.parseDouble(price);
		}
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("AllMenu", ListMap);
		return "/app/detail/clientOrderList.jsp";
	}
	
	/******-------------------------------订单的后台管理-----------------------------------------*****/
	/*
	 * 后台得到订单列表
	 */
	public Object getList(HttpServletRequest request,HttpServletResponse response){
		 List<Map<String, Object>> list = ordersService.getList();
		request.setAttribute("OrdersList", list);
		
		return "/sys/order/orderList.jsp";
	}
	
	/*
	 *得到订单列表的详细信息 
	 */
	public Object getDetailOrders(HttpServletRequest request,HttpServletResponse response){
		int ordersId=Integer.parseInt(request.getParameter("ordersId"));
		List<Map<String,Object>> ListMap =(List<Map<String,Object>>) ordersService.getAllMenuList(ordersId);
		request.setAttribute("AllMenu", ListMap);
		return "/sys/order/orderDetail.jsp";
	}
	
	/*
	 * 结账走人
	 */
	public Object doCheckout(HttpServletRequest request,HttpServletResponse response){
		int ordersId=Integer.parseInt(request.getParameter("ordersId"));
		ordersService.changeStatus(ordersId);
		return "/OrderServlet?method=getList";
	}

}
