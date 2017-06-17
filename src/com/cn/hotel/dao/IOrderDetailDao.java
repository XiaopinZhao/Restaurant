package com.cn.hotel.dao;

import java.util.List;

import com.cn.hotel.entity.OrderDetail;
/**
 * 对订orderdetail进行操作的接口
 */
public interface IOrderDetailDao{
	/*
	 * 添加订单详细列表
	 */
	public void addOrderDetail(OrderDetail orderDetail);
	/*
	 * 根据订单ID和食物ID查找订单详细信息
	 */
	public OrderDetail getOrderDetailByOrderIdandFood_id(OrderDetail orderDetail);
	/*
	 * 更新订单详细信息
	 */
	public void updateOrderDeail(int id,int count);
	/*
	 * 得到一共某种食物一共定了几次
	 */
	public int getFood_count(int id);
}
