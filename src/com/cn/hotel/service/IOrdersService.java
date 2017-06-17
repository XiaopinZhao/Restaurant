package com.cn.hotel.service;

import java.util.List;
import java.util.Map;

import com.cn.hotel.entity.OrderDetail;
import com.cn.hotel.entity.Orders;

public interface IOrdersService {
	/*
	 * 添加一条订单信息
	 */
	public void addOrders(Orders orders);
	/*
	 * 得到订单列表中最大的id值
	 */
	public int getOrdersId();
	/*
	 * 根据orders判断是否已经存在这个信息
	 */
	public Orders isExitOrders(Orders orders);
	/*
	 * 根据订单id更新订单详细信息
	 */
	public void updateOrders(OrderDetail orderDetail,int OrdersId);
	/*
	 * 得到最大的订单ID
	 */
	public int getMaxId();
	/*
	 * 得到所有的订单详细信息
	 */
	public List getAllMenuList(int ordersId);
	/*
	 * 得到一些信息
	 */
	public List<Map<String,Object>> getList();
	/*
	 * 修改订单是否结账状态
	 */
	public void changeStatus(int ordersId);
}
