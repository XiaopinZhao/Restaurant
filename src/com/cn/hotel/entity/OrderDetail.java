package com.cn.hotel.entity;
/**
 * 订单详细类
 */
public class OrderDetail {
	private int id;
	private int orderId;
	private int food_id;
	private int foodCount;
	public OrderDetail(int id, int orderId, int food_id, int foodCount) {
		this.id = id;
		this.orderId = orderId;
		this.food_id = food_id;
		this.foodCount = foodCount;
	}
	public OrderDetail() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	
}
