package com.cn.hotel.service.Impl;

import java.util.List;
import java.util.Map;

import com.cn.hotel.dao.IFoodDao;
import com.cn.hotel.dao.IOrderDetailDao;
import com.cn.hotel.dao.IOrdersDao;
import com.cn.hotel.dao.Impl.FoodDao;
import com.cn.hotel.dao.Impl.OrderDetailDao;
import com.cn.hotel.dao.Impl.OrdersDao;
import com.cn.hotel.entity.DinnerTable;
import com.cn.hotel.entity.OrderDetail;
import com.cn.hotel.entity.Orders;
import com.cn.hotel.factory.BeanFactory;
import com.cn.hotel.service.IDinnerTableService;
import com.cn.hotel.service.IOrdersService;

public class OrdersService implements IOrdersService {

	private IOrdersDao ordersDao=BeanFactory.getInstance("OrdersDao", OrdersDao.class);
	private IFoodDao foodDao=BeanFactory.getInstance("FoodDao", FoodDao.class);
	private IOrderDetailDao detailDao=BeanFactory.getInstance("OrderDetailDao", OrderDetailDao.class);
	private IDinnerTableService tableService=BeanFactory.getInstance("DinnerTableService", DinnerTableService.class);
	
	/*
	 * 添加一个订单信息
	 */
	public void addOrders(Orders orders) {
		ordersDao.addOrders(orders);
		
	}


	/*
	 * 得到最大的订单ID
	 */
	public int getOrdersId() {
		return ordersDao.getOrdersId();
	}

	/*
	 * 判断当前订单是否已经存在
	 */
	public Orders isExitOrders(Orders orders) {
		return ordersDao.isExitOrders(orders);
	}

	/*
	 * 修改订单信息的总金额
	 */
	public void updateOrders(OrderDetail orderDetail, int ordersId) {
		//需要在总价格里边加的价钱
		double price= foodDao.findFoodById(orderDetail.getFood_id()).getPrice()*orderDetail.getFoodCount();
		Orders ordersById = ordersDao.getOrdersById(ordersId);
		double totalPrice = ordersById.getTotalPrice();
		price=price+totalPrice;
		ordersDao.updateOrders(ordersId,price);
	}

	/*
	 * 得到最大的订单ID
	 */
	public int getMaxId() {
		return ordersDao.getOrdersId();
	}

	/*
	 * 得到所有的订单
	 */
	public List getAllMenuList(int ordersId) {
		return ordersDao.getAllMenuList( ordersId);
	}

	/*
	 * 得到orders表和food表的一些信息
	 */
	public List<Map<String,Object>> getList() {
		return ordersDao.getList();
	}


	/*
	 * 结账功能
	 */
	public void changeStatus(int ordersId) {
		Orders ordersById = ordersDao.getOrdersById(ordersId);
		int table_id=ordersById.getTable_id();
		DinnerTable table=new DinnerTable();
		table.setId(table_id);
		table.setTableStatus(0);
		tableService.backTable(table);
		ordersDao.changeStatus(ordersId);
		
	}
	
}
