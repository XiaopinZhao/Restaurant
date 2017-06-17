package com.cn.hotel.dao;

import java.util.List;

import com.cn.hotel.entity.DinnerTable;
/**
 * 对dinnertable进行操做的接口
 */
public interface IDinnerTableDao {
	/*
	 * 得到所有的餐桌信息
	 */
	public List<DinnerTable> getDinnerTableList();
	/*
	 * 根据id得到所有的餐桌信息
	 */
	public DinnerTable getDinnerTableById(int id);
	/*
	 * 添加一个餐桌信息
	 */
	public void addDinnerTable(DinnerTable dinnerTable);
	/*
	 * 删除一个餐桌信息
	 */
	public void deletDinnerTable(int id);
	/*
	 * 更新一个餐桌信息
	 */
	public void upDataDinnerTable(DinnerTable dinnerTable);
	/*
	 * 根据关键字查找餐桌信息
	 */
	public List<DinnerTable> query(String keyword);
}
