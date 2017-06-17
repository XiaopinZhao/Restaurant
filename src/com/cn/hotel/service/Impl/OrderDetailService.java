package com.cn.hotel.service.Impl;

import java.util.List;

import com.cn.hotel.dao.Impl.OrderDetailDao;
import com.cn.hotel.entity.OrderDetail;
import com.cn.hotel.factory.BeanFactory;
import com.cn.hotel.service.IOrderDetailService;

public class OrderDetailService implements IOrderDetailService{

	private OrderDetailDao detailDao=BeanFactory.getInstance("OrderDetailDao", OrderDetailDao.class);

	/*
	 * 添加一条订单详细信息
	 */
	public void addOrderDetail(OrderDetail orderDetail) {
		System.out.println("service ordersDetailID------"+orderDetail.getOrderId());
		detailDao.addOrderDetail(orderDetail);
	}

	/*
	 * 根据订单的id和食物的id得到订单详细的所有信息
	 */
	public OrderDetail getOrderDetailByOrderIdandFood_id(OrderDetail orderDetail) {
		return detailDao.getOrderDetailByOrderIdandFood_id(orderDetail);
	}
	
	/*
	 * 更新订单详细列表
	 */
	public void updateOrderDetail(OrderDetail orderDetail,int food_count) {
		//orderDetail.getFoodCount()-->原来有的，需要加food_count条
		int id=orderDetail.getId();//要变化的id
		int count=orderDetail.getFoodCount()+food_count;
		detailDao.updateOrderDeail(id,count);
	} 
	


}
