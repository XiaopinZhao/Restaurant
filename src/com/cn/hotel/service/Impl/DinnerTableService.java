package com.cn.hotel.service.Impl;


import java.util.Date;
import java.util.List;

import com.cn.hotel.dao.IDinnerTableDao;
import com.cn.hotel.dao.Impl.DinnerTableDao;
import com.cn.hotel.entity.DinnerTable;
import com.cn.hotel.factory.BeanFactory;
import com.cn.hotel.service.IDinnerTableService;


public class DinnerTableService implements IDinnerTableService{
	private IDinnerTableDao dao=BeanFactory.getInstance("DinnerTableDao", DinnerTableDao.class);
	/*
	 * 得到所有的订单详细信息
	 */
	public List<DinnerTable> getDinnerTableList() {
		List<DinnerTable> list = dao.getDinnerTableList();
		return list;
	}
	
	/*
	 * 添加一条餐桌信息
	 */
	public void addDinnerTable(DinnerTable table) {
		dao.addDinnerTable(table);
	}

	/*
	 * 删除一条餐桌信息
	 */
	public void deleteDinnerTable(int id) {
		dao.deletDinnerTable(id);
	}

	/*
	 * 退桌
	 */
	public void backTable(DinnerTable dinnerTable) {
		//得到dinnerTable的一些信息，并根据id找到已经存在的数据的信息
		DinnerTable table = dao.getDinnerTableById(dinnerTable.getId());
		//如果dinnerTable对应的属性为空，则将在数据库中找到的数据的属性值赋给dinnerTable
		if(dinnerTable.getTableName()==null || dinnerTable.getTableName().equals("")){
			dinnerTable.setTableName(table.getTableName());
		}
		//如果顶netTable对应的属性值不为空，则保留他的属性
		if(dinnerTable.getTableStatus()!=0&&dinnerTable.getTableStatus()!=1){
			dinnerTable.setTableStatus(table.getTableStatus());
		}
		dinnerTable.setOrderdate(null);
		dao.upDataDinnerTable(dinnerTable);
	}

	/*
	 * 改变餐桌信息的状态
	 */
	public void chageStatus(int id) {
		//根据id找到餐桌信息并且改变他的状态和订单日期
		DinnerTable table = dao.getDinnerTableById(id);
		int status=table.getTableStatus();
		if(status==0){
			status=1;
			table.setOrderdate(new Date());
		}else if(status==1){
			status=0;
			table.setOrderdate(null);
		}
		table.setTableStatus(status);
		dao.upDataDinnerTable(table);
	}

}
