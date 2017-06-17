package com.cn.hotel.entity;

import java.util.Date;
/**
 * 餐桌类
 */
public class DinnerTable {
	private int id;
	private String tableName;
	private int tableStatus=0;
	private Date orderdate;
	public DinnerTable(int id, String tableName, int tableStatus,
			Date orderdate) {
		this.id = id;
		this.tableName = tableName;
		this.tableStatus = tableStatus;
		this.orderdate = orderdate;
	}
	public DinnerTable() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	@Override
	public String toString() {
		return "DinnerTable [id=" + id + ", table_name=" + tableName
				+ ", tableStatus=" + tableStatus + ", orderdate=" + orderdate
				+ "]";
	}
	
}
