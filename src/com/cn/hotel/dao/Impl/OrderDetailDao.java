package com.cn.hotel.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.cn.hotel.dao.IOrderDetailDao;
import com.cn.hotel.entity.OrderDetail;
import com.cn.hotel.utils.JdbcUtil;
/**
 * 对orderdetail进行操作的实现类
 */
public class OrderDetailDao implements IOrderDetailDao{

	private QueryRunner qr=JdbcUtil.getQuery();
	
	/*
	 * 添加订单详细列表
	 */
	public void addOrderDetail(OrderDetail orderDetail) {
		String sql="insert into orderdetail (orderId,food_id,foodCount) values (?,?,?)";
		System.out.println("detailDao -------"+orderDetail.getOrderId());
		try{
				qr.update(sql,orderDetail.getOrderId(),orderDetail.getFood_id(),orderDetail.getFoodCount());
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 根据订单ID和食物ID查找订单详细信息
	 */
	public OrderDetail getOrderDetailByOrderIdandFood_id(OrderDetail orderDetail) {
		String sql="select * from orderdetail where food_id=? and orderId=?";
		OrderDetail order=null;
		try{
			order=qr.query(sql, new BeanHandler<OrderDetail>(OrderDetail.class),orderDetail.getFood_id(),orderDetail.getOrderId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return order;
	}

	/*
	 * 更新订单详细信息
	 */
	public void updateOrderDeail(int id,int count) {
		String sql="update orderdetail set foodCount=? where id=?";
		try{
			qr.update(sql,count,id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 得到一共某种食物一共定了几次
	 */
	public int getFood_count(int id) {
		String sql="select foodCount from orderdetail where id=?";
		int  count=0;
		try{
			Integer query = qr.query(sql, new ScalarHandler<Integer>(),id);
			System.out.println("query-->"+query);
			count=query.intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

}
