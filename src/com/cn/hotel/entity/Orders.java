package com.cn.hotel.entity;

import java.util.Date;

/**
 * 订单类
 */
public class Orders {
	private int id;
	private int table_id;
	private Date orderDate;
	private double totalPrice;
	private int orderStatus;
	public Orders(int id, int table_id, Date orderDate, double totalPrice,
			int orderStatus) {
		this.id = id;
		this.table_id = table_id;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
	}
	public Orders() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date date) {
		this.orderDate = date;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", table_id=" + table_id + ", orderDate="
				+ orderDate + ", totalPrice=" + totalPrice + ", orderStatus="
				+ orderStatus + "]";
	}
	
	
}
