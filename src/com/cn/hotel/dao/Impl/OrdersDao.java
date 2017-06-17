package com.cn.hotel.dao.Impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cn.hotel.dao.IOrdersDao;
import com.cn.hotel.entity.Orders;
import com.cn.hotel.utils.JdbcUtil;
/**
 * 对orders表进行操作的实现类
 */
public class OrdersDao implements IOrdersDao{

	private QueryRunner qr=JdbcUtil.getQuery();
	
	/*
	 * 添加订单信息
	 */
	public void addOrders(Orders orders) {
		String sql="insert into orders (table_id,orderDate,totalPrice,orderStatus) values (?,?,?,?)";
		Orders order=null;
		try{
			qr.update(sql,orders.getTable_id(),orders.getOrderDate(),orders.getTotalPrice(),orders.getOrderStatus());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * 得到订单信息的ID
	 */
	public int getOrdersId(){
		
		String sql="SELECT MAX(id) FROM orders;";
		int id=0;
		try{
			Integer query = qr.query(sql, new ScalarHandler<Integer>());
			id=query.intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

	/*
	 * 根据订单信息判断订单信息是否存在
	 */
	public Orders isExitOrders(Orders orders) {
		String sql="select * from orders where table_id=? and orderStatus=?";
		Orders order=null;
		try{
			order=qr.query(sql, new BeanHandler<Orders>(Orders.class),orders.getTable_id(),orders.getOrderStatus());
		}catch(Exception e){
			e.printStackTrace();
		}
		return order;
	}

	/*
	 * 根据ID查找订单信息
	 */
	public Orders getOrdersById(int id) {
		String sql="select * from orders where id=?";
		Orders order=null;
		try{
			order=qr.query(sql, new BeanHandler<Orders>(Orders.class),id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return order;
	}

	/*
	 * 更新订单信息
	 */
	public void updateOrders(int id, double price) {
		String sql="update orders set totalPrice=? where id=?";
		try{
			qr.update(sql,price,id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 根据订单信息查找订单
	 */
	public List getAllMenuList(int ordersId) {
		List<Map<String, Object>> listMap=null;
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT f.foodName,f.price,d.foodCount ");
		sql.append(" FROM orderdetail d ,food f ");
		sql.append(" WHERE 1=1");
		sql.append("    AND d.food_id=f.id ");
		sql.append("      AND d.orderId=?");
		try{
			listMap = qr.query(sql.toString(), new MapListHandler(),ordersId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return listMap;
	}

	/*
	 * 得到订单的一些信息
	 */
	public List<Map<String,Object>> getList() {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT dinnertable.tableName,orders.id,orders.orderDate,orders.totalPrice,orders.orderStatus ");
		sql.append("FROM orders ,dinnertable  ");
		sql.append("WHERE 1=1 AND dinnertable.id=orders.table_id");
		List<Map<String,Object>> list=null;
		try{
			list=qr.query(sql.toString(),new  MapListHandler());
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 改变订单缴费状态
	 */
	public void changeStatus(int ordersId) {
		String sql="UPDATE orders SET orderStatus=1 WHERE id=?";
		try{
			qr.update(sql,ordersId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	
}
