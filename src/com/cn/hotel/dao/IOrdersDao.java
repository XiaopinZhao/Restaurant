package com.cn.hotel.dao;

import java.util.List;
import java.util.Map;

import com.cn.hotel.entity.Orders;
/**
 * 对订单进行操作的接口
 */
public interface IOrdersDao {
	/*
	 * 添加订单信息
	 */
	public void addOrders(Orders orders);
	/*
	 * 得到订单信息的ID
	 */
	public int getOrdersId();
	/*
	 * 根据订单信息判断订单信息是否存在
	 */
	public Orders isExitOrders(Orders orders);
	/*
	 * 根据ID查找订单信息
	 */
	public Orders getOrdersById(int id);
	/*
	 * 更新订单信息
	 */
	public void updateOrders(int id,double price);
	/*
	 * 根据订单信息查找订单
	 */
	public List getAllMenuList(int ordersId);
	/*
	 * 得到订单的一些信息
	 */
	public List<Map<String,Object>> getList();
	/*
	 * 改变订单缴费状态
	 */
	public void changeStatus(int ordersId);
}
