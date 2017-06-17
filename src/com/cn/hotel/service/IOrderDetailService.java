package com.cn.hotel.service;

import java.util.List;

import com.cn.hotel.entity.OrderDetail;

public interface IOrderDetailService {
	/*
	 * 添加一条订单详细信息
	 */
	public void addOrderDetail(OrderDetail orderDetail);
	/*
	 * 根据订单的id和食物的id得到订单详细的所有信息
	 */
	public OrderDetail getOrderDetailByOrderIdandFood_id(OrderDetail orderDetail);
	/*
	 * 更新订单详细列表
	 */
	public void updateOrderDetail(OrderDetail orderDetail,int food_count);
}
