package com.cn.hotel.entity;
/**
 * 菜类别
 */
public class FoodType {
	private int id;
	private String typeName;
	public FoodType(int id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}
	public FoodType() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
