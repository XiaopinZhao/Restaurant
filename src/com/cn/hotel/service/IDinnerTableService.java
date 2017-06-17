package com.cn.hotel.service;

import java.util.List;

import com.cn.hotel.entity.DinnerTable;

public interface IDinnerTableService {
	/*
	 * 得到所有的餐桌信息
	 */
	public List<DinnerTable> getDinnerTableList();
	/*
	 * 添加一条餐桌信息
	 */
	public void addDinnerTable(DinnerTable table);
	/*
	 * 删除一条餐桌信息
	 */
	public void deleteDinnerTable(int id);
	/*
	 * 退桌
	 */
	public void backTable(DinnerTable dinnerTable);
	/*
	 * 改变餐桌信息的状态
	 */
	public void chageStatus(int id);
}
